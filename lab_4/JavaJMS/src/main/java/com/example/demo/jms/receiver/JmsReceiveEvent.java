package com.example.demo.jms.receiver;

import com.example.demo.jms.EmailRepository;
import com.example.demo.jms.WatchDogRepository;
import com.example.demo.jms.entities.Email;
import com.example.demo.jms.entities.WatchDog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiveEvent {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private WatchDogRepository watchDogRepository;


    @JmsListener(destination = "mail")
    public void receiveEmail(Email email) {
        emailRepository.save(email);
        System.err.println("Received <" + email + ">");
    }

    @JmsListener(destination = "event")
    public void receiveWatcherEvent(WatchDog event) {
        watchDogRepository.save(event);
        System.err.println("Received <" + event + ">");
    }
}
