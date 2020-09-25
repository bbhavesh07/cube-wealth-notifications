package com.cubewealth.notifications.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = EventDto.class)
public class EventDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
	
	private Long userid;
	private String ts;
	private String latlong;
	private String noun;
	private String verb;
	private int timespent;
	private Map<String, String> properties;
	
	public Timestamp getTimestamp() {
		Timestamp t = null;
		try {
			t = new Timestamp(dateFormat.parse(this.ts).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return t;
	}
	public double getLatitude() {
		return Double.valueOf(this.latlong.split(",")[0]);
	}
	public double getLongitude() {
		return Double.valueOf(this.latlong.split(",")[1]);
	}
	public boolean isBillPay() {
		return this.noun.equals("bill") && this.verb.equals("pay");
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getLatlong() {
		return latlong;
	}
	public void setLatlong(String latlong) {
		this.latlong = latlong;
	}
	public String getNoun() {
		return noun;
	}
	public void setNoun(String noun) {
		this.noun = noun;
	}
	public String getVerb() {
		return verb;
	}
	public void setVerb(String verb) {
		this.verb = verb;
	}
	public int getTimespent() {
		return timespent;
	}
	public void setTimespent(int timespent) {
		this.timespent = timespent;
	}
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
}
