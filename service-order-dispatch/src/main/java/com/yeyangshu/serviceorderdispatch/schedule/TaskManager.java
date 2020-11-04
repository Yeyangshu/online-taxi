package com.yeyangshu.serviceorderdispatch.schedule;

import com.yeyangshu.internalcommon.constant.CommonStatusEnum;
import com.yeyangshu.internalcommon.constant.DispatchConstant;
import com.yeyangshu.internalcommon.constant.OrderTypeEnum;
import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.serviceorder.dataobject.OrderRulePrice;
import com.yeyangshu.internalcommon.entity.serviceorder.dataobject.Order;
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
 * 任务管理器
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
     * @param orderId 订单id
     * @return 派单结果
     */
    @Async
    public ResponseResult dispatch(int orderId) {
        log.info("INFO TaskManager - prepare dispatch task, orderId:{}", orderId);

        // 微服务无状态，防止重复派单，订单去重
        if (taskStore.getResults().containsKey(orderId)) {
            log.info("INFO TaskManager - repeat order, orderId:{}", orderId);
            return null;
        }

        Order order = dispatchService.getOrderById(orderId);
        if (null == order) {
            log.warn("WARN TaskManager - order is null, orderId:{}", orderId);
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), ORDER_ID_IS_NULL);
        }

        // 乘客设备号，防止刷单
        if (StringUtils.isEmpty(order.getDeviceCode())) {
            log.warn("WARN TaskManager - device code is null, orderId:{}", orderId);
            return null;
        }

        // 下面的判断是确定任务分支
        // 计费明细表，serviceTypeId, cityCode
        OrderRulePrice orderRulePrice = dispatchService.getOrderRulePrice(orderId);

        int type = -1;
        if (null == order.getOrderStartTime()) {
            log.warn("WARN TaskManager - order start time is null, orderId:{}", orderId);
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), ORDER_START_TIME_IS_NULL);
        }

        List<TaskCondition> taskConditions = null;
        ITask task = null;
        // 查询当前城市是否打开了强派开关，
        boolean isOpenForce = configService.isOpenForceSendOrder(orderRulePrice.getCityCode());
        // 是否是实时订单，是否是假成功单，0：否，1：是
        if (order.getStatus() == DispatchConstant.ORDER_STATUS_ORDER_START && order.getFakeSuccess() == 0) {
            // 获取强制发送订单时间，小于30分钟，立即派单，强派
            int forceTime = configService.getForceSendOrderTime(orderRulePrice.getCityCode(), DispatchConstant.TIME_THRESHOLD_TYPE_FORCE);
            log.info("INFO TaskManager - force dispatch, orderId:{}, forceTime:{}", orderId, forceTime);
            if (order.getOrderStartTime().getTime() - System.currentTimeMillis() < TimeUnit.MINUTES.toMillis(forceTime)) {
                // 实时单
                if (order.getServiceType() != OrderTypeEnum.REAL_TIME.getCode()) {
                    if (!isOpenForce) {
                        log.info("INFO TaskManager - force switch off, cityCode:{}", orderRulePrice.getCityCode());
                        return null;
                    }
                }
                type = OrderTypeEnum.FORCE.getCode();
                log.info("INFO TaskManager - start dispatch task, orderId:{}", orderId);
                // 轮数
                int round = 1;
                if (order.getFakeSuccess() == 1) {
                    log.info("INFO TaskManager - fake success order, orderId:{}", orderId);
                    round = 3;
                }
                // 派单规则，一轮一个task，多轮，list集合
                taskConditions = dispatchService.getForceTaskCondition(orderRulePrice.getCityCode(), order.getServiceType(), round);
                task = OrderTaskFactory.createTask(orderId, order.getServiceType(), type);
            } else {
                // 特殊时段
                boolean isSpecial = dispatchService.isSpecial(orderRulePrice.getCityCode(), orderRulePrice.getServiceTypeId(), order.getOrderStartTime().getTime());
                if (isSpecial) {
                    type = OrderTypeEnum.SPECIAL.getCode();
                    log.info("INFO TaskManager - reservation order, special time, orderId:{}", orderId);
                    taskConditions = dispatchService.getSpecialCondition(orderRulePrice.getCityCode(), orderRulePrice.getServiceTypeId());
                } else {
                    type = OrderTypeEnum.NORMAL.getCode();
                    log.info("INFO TaskManager - reservation order, normal time, orderId:{}", orderId);
                    taskConditions = dispatchService.getNormalCondition(orderRulePrice.getCityCode(), orderRulePrice.getServiceTypeId());
                }
                task = OrderTaskFactory.createTask(orderId, order.getServiceType(), type);
            }
        }

        if (order.getStatus() == DispatchConstant.ORDER_STATUS_ORDER_START && order.getFakeSuccess() == 1) {
            if (!isOpenForce) {
                log.info("INFO TaskManager - force switch off, cityCode:{}", orderRulePrice.getCityCode());
                return null;
            }
            if (order.getOrderStartTime().getTime() - System.currentTimeMillis() < TimeUnit.MINUTES.toMillis(configService.getForceSendOrderTime(orderRulePrice.getCityCode(), order.getServiceType()))) {
                type = OrderTypeEnum.FORCE.getCode();
                log.info("INFO TaskManager - start dispatch task, orderId:{}", orderId);
                //轮数
                int round = 1;
                if (order.getFakeSuccess() == 1) {
                    log.info("INFO TaskManager - fake success order, orderId:{}", orderId);
                    round = 3;
                }
                //派单规则，一轮一个task，多轮
                task = OrderTaskFactory.createTask(orderId, order.getServiceType(), type);
                taskConditions = dispatchService.getForceTaskCondition(orderRulePrice.getCityCode(), order.getServiceType(), round);
            }
        }

        if (taskConditions == null) {
            log.error("ERROR TaskManager - task conditions is null , orderId:{}", orderId);
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), TASK_CONDITIONS_IS_NULL);
        }
        if (null == task) {
            log.error("ERROR TaskManager - task is null, orderId:{}", orderId);
            return null;
        }
        // taskConditions加入task
        task.setTaskConditions(taskConditions);
        log.info("INFO TaskManager - execute task, orderId:{}, taskType:{}", orderId, type);
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