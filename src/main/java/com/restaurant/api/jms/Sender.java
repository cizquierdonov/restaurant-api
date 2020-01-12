package com.restaurant.api.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sender {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${jms.queue}")
	private String jmsQueue;

	public void send(String message) {
		jmsTemplate.convertAndSend(jmsQueue, message);
	}

}
