package com.alirza.rest.webservices.restwebservices.helloworld;

public class HelloWorldBean {
	private String text;

	public HelloWorldBean(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
