package com.vigneshsn.springbootjmsdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MessageProducer {


    private JmsTemplate jmsTemplate;

    @Autowired
    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void produceMessage(){
        System.out.println("inside message producer");
        jmsTemplate.convertAndSend("test", "Hi!!! " + new Random().nextInt());
    }

}
