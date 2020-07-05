package com.deutshe.crawler.rest.models;

import java.util.Set;

public class Response {

	private int total_links;
	private int total_images;
	private Set<ChildResponse> details;
	
	public int getTotal_links() {
		return total_links;
	}
	public void setTotal_links(int total_links) {
		this.total_links = total_links;
	}
	public int getTotal_images() {
		return total_images;
	}
	public void setTotal_images(int total_images) {
		this.total_images = total_images;
	}
	public Set<ChildResponse> getDetails() {
		return details;
	}
	public void setDetails(Set<ChildResponse> details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "Response [total_links=" + total_links + ", total_images=" + total_images + ", details=" + details + "]";
	}
	
	
	
}
