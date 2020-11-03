package com.yeyangshu.serviceorderdispatch.schedule;

import com.yeyangshu.internalcommon.constant.CommonStatusEnum;
import com.yeyangshu.internalcommon.constant.DispatchConstant;
import com.yeyangshu.internalcommon.constant.OrderTypeEnum;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.serviceorder.OrderRulePrice;
import com.yeyangshu.internalcommon.dto.serviceorder.dataobject.Order;
import com.yeyangshu.serviceorderdispatch.service.ConfigService;
import com.yeyangshu.serviceorderdispatch.service.DispatchService;
import com.yeyangshu.serviceorderdispatch.task.ITask;
import com.yeyangshu.serviceorderdispatch.task.OrderForceTask;
import com.yeyangshu.serviceorderdispatch.task.OrderTaskFactory;
import com.yeyangshu.serviceorderdispatch.task.TaskCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务
 *
 * @author yeyangshu
 */
@Service
@Slf4j
public class TaskManager {

    private static final String ORDER_ID_IS_NULL = "订单ID为空";

    private static final String ORDER_START_TIME_IS_NULL = "订单开始时间为空";

    private static final String TASK_CONDITIONS_IS_NULL = "任务为空";

    @Autowired
    private TaskStore taskStore;

    @Autowired
    private DispatchService dispatchService;

    @Autowired
    private ConfigService configService;

    /**
     * 派单
     *
     * @param orderId
     * @return
     */
    @Async
    public ResponseResult dispatch(int orderId) {
        // 派单任务，定时执行

        // 微服务无状态，重复派单，去重
        if (taskStore.getResults().containsKey(orderId)) {
            log.info("#orderId = " + orderId + " 重复派单");
            return null;
        }
        Order order = dispatchService.getOrderById(orderId);
        if (null == order) {
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), ORDER_ID_IS_NULL);
        }
        // 乘客设备号，防止刷单（钻空子）
        if (StringUtils.isEmpty(order.getDeviceCode())) {
            log.info("#orderId = " + orderId + "  device code null");
            return null;
        }
        // 下面的判断是确定任务分支
        // 计费明细表，serviceTypeId, cityCode
        OrderRulePrice orderRulePrice = dispatchService.getOrderRulePrice(orderId);
        int type = -1;
        if (null == order.getOrderStartTime()) {
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), ORDER_START_TIME_IS_NULL);
        }
        List<TaskCondition> taskConditions = null;
        ITask task = null;
        // 查询是否打开了强派开关，依据城市
        boolean isOpenForce = configService.isOpenForceSendOrder(orderRulePrice.getCityCode());
        // 是否是实时订单，是否是假成功单，0：否，1：是
        if (order.getStatus() == DispatchConstant.ORDER_STATUS_ORDER_START && order.getFakeSuccess() == 0) {
            // 获取强制发送订单时间，小于30分钟，立即派单，强派
            int forceTime = configService.getForceSendOrderTime(orderRulePrice.getCityCode(), DispatchConstant.TIME_THRESHOLD_TYPE_FORCE);
            log.info("#orderId = " + orderId + " 强派时间设置 = " + forceTime);
            if (order.getOrderStartTime().getTime() - System.currentTimeMillis() < TimeUnit.MINUTES.toMillis(forceTime)) {
                // 实时单
                if (order.getServiceType() != OrderTypeEnum.REAL_TIME.getCode()) {
                    if (!isOpenForce) {
                        log.info("#orderId = " + orderId + " 强派没开启");
                        return null;
                    }
                }
                type = OrderTypeEnum.FORCE.getCode();
                log.info("dispatch------强派");
                // 轮数
                int round = 1;
                if (order.getFakeSuccess() == 1) {
                    log.info("dispatch------加成功强派");
                    round = 3;
                }
                // 派单规则，一轮一个task，多轮，list集合
                taskConditions = dispatchService.getForceTaskCondition(orderRulePrice.getCityCode(), order.getServiceType(), round);
                task = new OrderForceTask(orderId, type);
            } else {
                // 特殊时段
                boolean isSpecial = dispatchService.isSpecial(orderRulePrice.getCityCode(), orderRulePrice.getServiceTypeId(), order.getOrderStartTime().getTime());
                if (isSpecial) {
                    type = OrderTypeEnum.SPECIAL.getCode();
                    log.info("dispatch------预约单，特殊时段");
                    taskConditions = dispatchService.getSpecialCondition(orderRulePrice.getCityCode(), orderRulePrice.getServiceTypeId());
                } else {
                    type = OrderTypeEnum.NORMAL.getCode();
                    log.info("dispatch------预约单，普通时段");
                    taskConditions = dispatchService.getNormalCondition(orderRulePrice.getCityCode(), orderRulePrice.getServiceTypeId());
                }
                task = OrderTaskFactory.createTask(orderId, order.getServiceType(), type);
            }
        }
        if (order.getStatus() == DispatchConstant.ORDER_STATUS_ORDER_START && order.getFakeSuccess() == 1) {
            if (!isOpenForce) {
                log.info("#orderId = " + orderId + " 强派没开启");
                return null;
            }
            if (order.getOrderStartTime().getTime() - System.currentTimeMillis() < TimeUnit.MINUTES.toMillis(configService.getForceSendOrderTime(orderRulePrice.getCityCode(), order.getServiceType()))) {
                type = OrderTypeEnum.FORCE.getCode();
                log.info("dispatch------强派");
                //轮数
                int round = 1;
                if (order.getFakeSuccess() == 1) {
                    log.info("dispatch------加成功强派");
                    round = 3;
                }
                //派单规则，一轮一个task，多轮
                task = new OrderForceTask(orderId, type);
                taskConditions = dispatchService.getForceTaskCondition(orderRulePrice.getCityCode(), order.getServiceType(), round);
            }
        }

        if (taskConditions == null) {
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), TASK_CONDITIONS_IS_NULL);
        }
        if (task == null) {
            log.error("#orderId = " + orderId + " task = null");
            return null;
        }
        log.info("#orderId = " + orderId + " type = " + type);

        // taskConditions加入task
        task.setTaskConditions(taskConditions);
        int status = task.execute(System.currentTimeMillis());
        if (status != -1) {
            // 添加task任务
            taskStore.addTask(task.getTaskId(), task);
        }
        return ResponseResult.success("派单成功");
    }

    /**
     * 重新派单
     *
     * @param task
     */
    @Async
    public void retry(ITask task) {
        int status = task.execute(System.currentTimeMillis());
        if (status != -1) {
            taskStore.addTask(task.getTaskId(), task);
        }
    }

}