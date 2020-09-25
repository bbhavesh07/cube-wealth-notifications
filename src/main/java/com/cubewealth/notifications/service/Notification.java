package com.cubewealth.notifications.service;

import java.sql.Date;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cubewealth.notifications.dto.EventDto;
import com.cubewealth.notifications.model.Event;
import com.cubewealth.notifications.repository.EventRepository;

@Service
public class Notification {

	private static final Logger logger = LoggerFactory.getLogger(Notification.class);
	
	@Autowired
	private EventRepository eventRepo;
	
	//Only bill pay events are pushed to RabbitMQ
	
	public void processFirstPaymentNotif(EventDto event) {
		if(eventRepo.findTop1ByUseridAndBillPay(event.getUserid(), PageRequest.of(0, 2)).size() == 1) {
			logger.info("This was the first payment for user " + event.getUserid());
		}
	}
	
	public void paymentsWithin5MinNotif(EventDto event) {
		List<Event> list = eventRepo.findPaymentsWithin5MinByUserid(event.getUserid(), new Date(event.getTimestamp().getTime() - TimeUnit.MINUTES.toMillis(5)), new Date(event.getTimestamp().getTime()));
		int cnt = 0;
		for(Event e: list) {
			if(Double.valueOf((String)e.getProperties().getOrDefault("value", "0.0")) >= 20000)
				cnt++;
			if(cnt >= 5) {
				logger.info("More than 5 payments of amount >=20000 within 5 min by user " + event.getUserid());
				return;
			}
		}
	}
	
	public void noFeedbackWithin15MinNotif(EventDto event) {
		Timer timer = new Timer(true);
		FeedbackScheduler task = new FeedbackScheduler(event, eventRepo);
		timer.schedule(task, 15*60*1000); //15min
	}
}


