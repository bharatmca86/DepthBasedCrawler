package com.deutshe.crawler.rest.models;

public class DeepCrawlerRequest {

	private String baseUrl;
	private int depth;
	
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	@Override
	public String toString() {
		return "DeepCrawlerRequest [baseUrl=" + baseUrl + ", depth=" + depth + "]";
	}
	
	
}
