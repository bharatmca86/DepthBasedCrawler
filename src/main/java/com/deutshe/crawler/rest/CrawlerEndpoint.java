package com.deutshe.crawler.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deutshe.crawler.rest.models.DeepCrawlerRequest;
import com.deutshe.crawler.rest.models.Response;
import com.deutshe.crawler.service.CrawlerService;
/**
 * 
 * @author bharat
 *
 */
@RestController
public class CrawlerEndpoint {

	@Autowired
	private CrawlerService service;
	
	@PostMapping(value="/submit", produces= {MediaType.APPLICATION_JSON_VALUE})
	public String submit(@RequestBody DeepCrawlerRequest request) {
		return service.submit(request.getBaseUrl(), request.getDepth());
	}
	
	@GetMapping(value="/all", produces= {MediaType.APPLICATION_JSON_VALUE})
	public Response fetchAll() {
		return service.fetchAll();
	}
	
	@GetMapping(value="/status", produces= {MediaType.APPLICATION_JSON_VALUE})
	public Response fetchAllByStatus(@RequestParam("status") String status) {
		return service.fetchAllByStatus(status);
	}
}
