package com.vigneshsn.springbootjmsdemo.orderreceiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;


public class OrderService {

    Logger log = LoggerFactory.getLogger(getClass());

//    @Qualifier("inboundReplyChannel")
//    private MessageChannel replyChannel;

    public Message<?> processOrder(Message<?> order){
        log.info("Message recived in OrderService {}", order);
        String correlationId = (String) order.getHeaders().get("jms_correlationId");
        log.info("CorrelationId {}", correlationId);
        return MessageBuilder.withPayload("Order Accepted")
                .setHeader("jms_correlationId", correlationId)
                .setReplyChannelName("inboundReplyChannel").build();
    }
}
