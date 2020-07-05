package com.deutshe.crawler.rest.models;

public class ChildResponse {

	private String page_title;
	private String page_link;
	private int image_count;
	
	public String getPage_title() {
		return page_title;
	}
	public void setPage_title(String page_title) {
		this.page_title = page_title;
	}
	public String getPage_link() {
		return page_link;
	}
	public void setPage_link(String page_link) {
		this.page_link = page_link;
	}
	public int getImage_count() {
		return image_count;
	}
	public void setImage_count(int image_count) {
		this.image_count = image_count;
	}
	
	@Override
	public String toString() {
		return "ChildResponse [page_title=" + page_title + ", page_link=" + page_link + ", image_count=" + image_count
				+ "]";
	}
	
	
}
