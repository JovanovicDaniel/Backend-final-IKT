package com.schoollog.models;

public class EmailObject {

	private String to;
	private String subject;
	private String text;
	
	
	public EmailObject() {
		super();
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subjevt) {
		this.subject = subjevt;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	
}
