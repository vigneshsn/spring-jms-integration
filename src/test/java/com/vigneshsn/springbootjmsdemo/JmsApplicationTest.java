package com.vigneshsn.springbootjmsdemo;

import com.vigneshsn.springbootjmsdemo.orderproducer.OrderProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
public class JmsApplicationTest {

//    @Autowired
//    private ApplicationContext applicationContext;

//    @Test
//    public void testIntegration() {
//        MessageChannel outboundRequestChannel = applicationContext.getBean("outboundRequestChannel", MessageChannel.class);
//        outboundRequestChannel.send(new GenericMessage<>("Spring integration working"));
//        QueueChannel outboundResponseChannel = applicationContext.getBean("outboundReplyChannel", QueueChannel.class);
//        assertThat(outboundResponseChannel.receive(5000).getPayload()).isEqualTo("Accepted");
//    }

    @Autowired
    private OrderProducerService orderProducerService;

    @Test
    public void test() {
        orderProducerService.createOrder();
    }
}
