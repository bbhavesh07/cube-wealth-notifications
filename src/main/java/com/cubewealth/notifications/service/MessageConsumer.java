package com.cubewealth.notifications.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubewealth.notifications.dto.EventDto;

@Component
public class MessageConsumer {
	
	@Autowired
	private Notification notif;
	
	@RabbitListener(queues="${cubewealth.rabbitmq.queue}", containerFactory="rabbitListenerFactory")
	public void recievedMessage(EventDto event) {
		notif.processFirstPaymentNotif(event);
		notif.paymentsWithin5MinNotif(event);
		notif.noFeedbackWithin15MinNotif(event);
	}
}