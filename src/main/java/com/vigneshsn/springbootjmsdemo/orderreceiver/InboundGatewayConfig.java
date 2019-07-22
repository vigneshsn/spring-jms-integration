package com.vigneshsn.springbootjmsdemo.orderreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.jms.ChannelPublishingJmsMessageListener;
import org.springframework.integration.jms.JmsInboundGateway;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.messaging.MessageChannel;

import javax.jms.ConnectionFactory;

@Configuration
public class InboundGatewayConfig {

    @Value("${inbound.order.request.queue}")
    private String inboundRequestDestination;

    @Value("${inbound.order.reply.queue}")
    private String inboundReplyDestination;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public MessageChannel inboundRequestChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel inboundReplyChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "inboundRequestChannel")
    public OrderService orderService() {
        return new OrderService();
    }

    @Bean
    public JmsInboundGateway jmsInboundGateway() {
        JmsInboundGateway jmsInboundGateway = new JmsInboundGateway(simpleMessageListenerContainer(),
                channelPublishingJmsMessageListener() );
        jmsInboundGateway.setRequestChannel(inboundRequestChannel());
        jmsInboundGateway.setReplyChannel(inboundReplyChannel());
        return jmsInboundGateway;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setDestinationName(inboundRequestDestination);
        return simpleMessageListenerContainer;
    }

    @Bean
    public ChannelPublishingJmsMessageListener channelPublishingJmsMessageListener() {
        ChannelPublishingJmsMessageListener channelPublishingJmsMessageListener =
                new ChannelPublishingJmsMessageListener();
        channelPublishingJmsMessageListener.setExpectReply(true);
        return channelPublishingJmsMessageListener;
    }
}
