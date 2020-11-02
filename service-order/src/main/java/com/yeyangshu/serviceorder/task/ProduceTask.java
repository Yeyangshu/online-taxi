package com.yeyangshu.serviceorder.task;

import com.yeyangshu.serviceorder.mapper.TblOrderEventDao;
import com.yeyangshu.serviceorder.entity.TblOrderEvent;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;
import java.util.List;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/26 22:24
 */
@Component
public class ProduceTask {

    @Autowired
    private TblOrderEventDao tblOrderEventDao;

    @Autowired
    private Queue queue;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Scheduled(cron = "0/5 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task() {
        System.out.println("定时任务");

        List<TblOrderEvent> tblOrderEventList = tblOrderEventDao.selectByOrderType("1");
        for (int i = 0; i < tblOrderEventList.size(); i++) {
            TblOrderEvent event = tblOrderEventList.get(i);

            // 更改这条数据的orderType为2
            tblOrderEventDao.updateEvent(event.getOrderType());
            System.out.println("修改数据库完成");

            jmsMessagingTemplate.convertAndSend(queue, JSONObject.fromObject(event).toString());
        }
    }
}
