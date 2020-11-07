package com.yeyangshu.servicepay.task;

import com.yeyangshu.internalcommon.entity.servicepay.dataobject.OrderEvent;
import com.yeyangshu.servicepay.dao.PayEventDao;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;
import java.util.List;

/**
 * 订单消息事件表定时任务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/7 19:45
 */
@Component
@Slf4j
public class PayEventTask {

    @Autowired
    private PayEventDao payEventDao;

    @Autowired
    private Queue queue;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Scheduled(cron="0/5 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task(){
        log.info("INFO OrderEventTask - start scheduled task");
        List<OrderEvent> tblOrderEventList = payEventDao.listByOrderType("1");
        for (OrderEvent event : tblOrderEventList) {
            // 更改这条数据的orderType为2
            payEventDao.updateEvent(event.getOrderType());
            log.info("INFO OrderEventTask - update database success, eventId:{}", event.getId());
            jmsMessagingTemplate.convertAndSend(queue, JSONObject.fromObject(event).toString());
        }
    }
}
