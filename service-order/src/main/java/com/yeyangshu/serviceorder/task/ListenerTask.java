package com.yeyangshu.serviceorder.task;

import com.yeyangshu.internalcommon.entity.servicepay.dataobject.OrderEvent;
import com.yeyangshu.serviceorder.mapper.TblOrderEventDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/7 20:05
 */
@Component
public class ListenerTask {

    @Autowired
    private TblOrderEventDao tblOrderEventDao;

    /**
     * 监听并处理消息
     *
     * @param textMessage
     * @param session
     * @throws JMSException
     */
    @JmsListener(destination = "ActiveMQQueue", containerFactory = "jmsListenerContainerFactory")
    public void receive(TextMessage textMessage, Session session) throws JMSException {
        try {
            System.out.println("收到的消息");
            String content = textMessage.getText();
            OrderEvent tblOrderEvent = (OrderEvent) JSONObject.toBean(JSONObject.fromObject(content), OrderEvent.class);
            tblOrderEventDao.insert(tblOrderEvent);
            // 业务完成，确认消息，消费成功
            textMessage.acknowledge();
        } catch (JMSException e) {
            // 回滚消息
            e.printStackTrace();
            System.out.println("异常了");
            session.recover();
        }
    }

    /**
     * 补偿 处理（人工，脚本）。自己根据自己情况。
     *
     * @param text
     */
    @JmsListener(destination = "DLQ.ActiveMQQueue")
    public void handleDeadLetter(String text) {
        System.out.println("死信队列:" + text);
    }
}
