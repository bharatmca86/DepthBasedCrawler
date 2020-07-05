package com.deutshe.crawler.rest.models;

public enum Status {
	SUBMITTED("Submitted"), IN_PROCESS("In-Process"), PROCESSED("Processed"), FAILED("Failed");
	
	String value;
	
	private Status(String value) {
		this.value = value;
	}
	
	public Status getValue() {
		return Status.valueOf(value);
	}
}
