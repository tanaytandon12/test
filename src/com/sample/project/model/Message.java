package com.sample.project.model;

public class Message {
	
	private String id;
	private String type;
	private String timeStamp;
	private String data;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public void setTimeStamp(String time) {
		this.timeStamp = time;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getTimeStamp() {
		return this.timeStamp;
	}
	
	public String getData() {
		return this.data;
	}
}
