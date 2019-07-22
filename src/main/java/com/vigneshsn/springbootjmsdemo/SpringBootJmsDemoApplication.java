package com.vigneshsn.springbootjmsdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import sun.plugin2.message.Message;

@SpringBootApplication
@EnableJms
public class SpringBootJmsDemoApplication  {

//	@Autowired
//	private MessageProducer messageProducer;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJmsDemoApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		messageProducer.produceMessage();
//	}
}

