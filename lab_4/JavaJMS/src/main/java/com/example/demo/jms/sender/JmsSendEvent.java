package com.example.demo.jms.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSendEvent {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendEvent(String topic, Object event) {
        jmsTemplate.convertAndSend(topic, event);
    }
}
