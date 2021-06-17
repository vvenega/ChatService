package com.example.demo;

import java.io.Serializable;

public class MessageBean implements Serializable {

	private String message;
	private String date;
	private String sender;
	private String receiver;
	private String channel;
	private String objectid;
	private String namesender;
	private String namereceiver;
	
	
	private String id;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getObjectid() {
		return objectid;
	}
	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNamesender() {
		return namesender;
	}
	public void setNamesender(String namesender) {
		this.namesender = namesender;
	}
	public String getNamereceiver() {
		return namereceiver;
	}
	public void setNamereceiver(String namereceiver) {
		this.namereceiver = namereceiver;
	}

	
	
	
}
