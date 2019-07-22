package com.vigneshsn.springbootjmsdemo.orderproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.jms.JmsOutboundGateway;
import org.springframework.messaging.MessageChannel;

import javax.jms.ConnectionFactory;

@Configuration
public class OutboundGatewayConfig {

    @Value("${outbound.order.request.queue}")
    private String outboundRequestDestination;

    @Value("${outbound.order.reply.queue}")
    private String outboundReplyDestination;

    @Autowired
    private ConnectionFactory jmsConnectionFactory;

    @Bean
    public MessageChannel outboundRequestChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outboundReplyChannel() {
        return new QueueChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "outboundRequestChannel")
    public JmsOutboundGateway jmsOutboundGateway() {
        JmsOutboundGateway jmsOutboundGateway = new JmsOutboundGateway();
        jmsOutboundGateway.setConnectionFactory(jmsConnectionFactory);
        jmsOutboundGateway.setRequestDestinationName(outboundRequestDestination);
        jmsOutboundGateway.setReplyDestinationName(outboundReplyDestination);
        return jmsOutboundGateway;
    }

    @Bean
    public OrderProducerService orderProducerService() {
        return new OrderProducerService(outboundRequestChannel(), outboundReplyChannel());
    }
}