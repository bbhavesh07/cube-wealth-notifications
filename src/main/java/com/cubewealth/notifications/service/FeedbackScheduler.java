package com.cubewealth.notifications.service;

import java.sql.Date;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cubewealth.notifications.dto.EventDto;
import com.cubewealth.notifications.repository.EventRepository;


public class FeedbackScheduler extends TimerTask {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedbackScheduler.class);
	
	private EventRepository eventRepo;
	
	private EventDto event;
	
	public FeedbackScheduler(EventDto event, EventRepository eventRepo) {
		this.event = event;
		this.eventRepo = eventRepo;
	}
	
	@Override
	public void run() {
		long cnt = eventRepo.findFeedbackWithin15MinByUserid(event.getUserid(), new Date(event.getTimestamp().getTime()));
		if(cnt == 0)
			logger.info("No feedback sent within 15 mins for a payment by user " + event.getUserid());
	}
}