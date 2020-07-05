package com.deutshe.crawler.service;

import com.deutshe.crawler.rest.models.Response;
/**
 * 
 * @author bharat
 *
 */
public interface CrawlerService {

	String submit(String baseUrl, int depth);

	Response fetchAll();
	
	Response fetchAllByStatus(String status);

}