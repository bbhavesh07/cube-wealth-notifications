package com.cubewealth.notifications.model;

import java.sql.Timestamp;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cubewealth.notifications.utilities.JsonToMapConverter;

@Entity
@Table
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long eventId;
	private Long userid;
	private Timestamp timestamp;
	private double latitude;
	private double longitude;
	@Enumerated(EnumType.STRING)
	private Noun noun;
	@Enumerated(EnumType.STRING)
	private Verb verb;
	private int timespent;
	@Column(columnDefinition = "TEXT") 
	@Convert(converter = JsonToMapConverter.class)
	private Map<String, Object> properties;
	
	public Long getId() {
		return eventId;
	}
	public void setId(Long eventId) {
		this.eventId = eventId;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userId) {
		this.userid = userId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp time) {
		this.timestamp = time;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Noun getNoun() {
		return noun;
	}
	public void setNoun(Noun noun) {
		this.noun = noun;
	}
	public Verb getVerb() {
		return verb;
	}
	public void setVerb(Verb verb) {
		this.verb = verb;
	}
	public int getTimespent() {
		return timespent;
	}
	public void setTimespent(int timeSpent) {
		this.timespent = timeSpent;
	}
	public Map<String, Object> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
