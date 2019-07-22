package com.vigneshsn.springbootjmsdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReciever {

    Logger log = LoggerFactory.getLogger(getClass());
    @JmsListener(destination = "test")
    public void receiveMessage(String message) {
        log.info("message received from queue test {}", message);
    }
}
