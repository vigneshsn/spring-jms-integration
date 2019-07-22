package com.vigneshsn.springbootjmsdemo.orderproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

public class OrderProducerService {

    Logger log = LoggerFactory.getLogger(getClass());


    public MessageChannel outboundRequestChannel;
    public MessageChannel outboundReplyChannel;

    public OrderProducerService(MessageChannel outboundRequestChannel, MessageChannel outboundReplyChannel) {
        this.outboundRequestChannel = outboundRequestChannel;
        this.outboundReplyChannel = outboundReplyChannel;
    }

    public void createOrder() {

        //Message<String> message = new GenericMessage<>("Spring integration working");
        Message<String> message = MessageBuilder.withPayload("spring integration jms")
                .setReplyChannel(outboundReplyChannel)
                .setHeader("jms_correlationId", UUID.randomUUID()).build();
        outboundRequestChannel.send(message);

        //Thread.sleep(5000);
        String reply = (String) ((QueueChannel) outboundReplyChannel).receive(5000)
                .getPayload();
        log.info("reply for orderService {}", reply);
    }
}
