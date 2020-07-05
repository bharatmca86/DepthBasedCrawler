package com.deutshe.crawler.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutshe.crawler.repository.CrawlerRepository;
import com.deutshe.crawler.repository.dto.CrawledEntity;
import com.deutshe.crawler.rest.models.ChildResponse;
import com.deutshe.crawler.rest.models.Response;
import com.deutshe.crawler.rest.models.Status;
/**
 * 
 * @author bharat
 *
 */
@Service
public class CrawlerServiceImpl implements CrawlerService {

	private static final String HREF_LINKS_ATTR = "abs:href";
	private static final String IMG_SRC_ATTR = "img[src~=(?i)\\.(png|jpe?g|gif)]";
	private static final String A_HREF_ATTR = "a[href]";
	
	@Autowired
	private CrawlerRepository repository;
	
	
	/* (non-Javadoc)
	 * @see com.deutshe.crawler.service.CrawlerService#submit(java.lang.String, int)
	 */
	@Override
	public String submit(String baseUrl, int depth) {
		Queue<String> links = new LinkedList<>();
		links.add(baseUrl);
		while (!links.isEmpty()) {
			CrawledEntity entity = new CrawledEntity();
			String url = links.poll();
			entity.setUrl(url);
			try {
				System.out.println(">> Depth: " + depth + " [" + url + "]");
				Document document = Jsoup.connect(url).get();
				Elements linksOnPage = document.select(A_HREF_ATTR);
				Elements imagesOnPage = document.select(IMG_SRC_ATTR);
				entity.setImageCount(imagesOnPage.size());
				entity.setPageTitle(document.title());
				entity.setStatus(Status.SUBMITTED.ordinal());
				repository.save(entity);
				if(depth > 0) {
					entity.setStatus(Status.IN_PROCESS.ordinal());
					repository.save(entity);
					for (Element page : linksOnPage) {
						links.add(page.attr(HREF_LINKS_ATTR));
					}
					entity.setStatus(Status.PROCESSED.ordinal());
					repository.save(entity);
					depth--;
				}				
			} catch (IOException e) {
				System.err.println("For '" + baseUrl + "': " + e.getMessage());
				entity.setStatus(Status.FAILED.ordinal());
				repository.save(entity);
			}

		}
		
		return Status.PROCESSED.name();
	}
	
	public static void main(String[] args) {
		System.out.println(new CrawlerServiceImpl().submit("http://tutorials.jenkov.com/", 2));
	}
	
	/* (non-Javadoc)
	 * @see com.deutshe.crawler.service.CrawlerService#submitted(java.lang.String, int)
	 */
	@Override
	public Response fetchAll() {
		Response response = new Response();
		Set<ChildResponse> detailsSet = new HashSet<>();
		int images = 0;
		List<CrawledEntity> entities = repository.findAll();
		for(CrawledEntity entity: entities) {
			ChildResponse details = new ChildResponse();
			details.setImage_count(entity.getImageCount());
			details.setPage_link(entity.getUrl());
			details.setPage_title(entity.getPageTitle());
			images += entity.getImageCount();
			detailsSet.add(details);
		}
		response.setDetails(detailsSet);
		response.setTotal_images(images);
		response.setTotal_links(entities.size());
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.deutshe.crawler.service.CrawlerService#inProcess(java.lang.String, int)
	 */
	@Override
	public Response fetchAllByStatus(String status) {
		Response response = new Response();
		Set<ChildResponse> detailsSet = new HashSet<>();
		int images = 0;
		Status statusEnum = lookup(Status.class, status);
		List<CrawledEntity> entities = repository.findByStatus(statusEnum.ordinal());
		for(CrawledEntity entity: entities) {
			ChildResponse details = new ChildResponse();
			details.setImage_count(entity.getImageCount());
			details.setPage_link(entity.getUrl());
			details.setPage_title(entity.getPageTitle());
			images += entity.getImageCount();
			detailsSet.add(details);
		}
		response.setDetails(detailsSet);
		response.setTotal_images(images);
		response.setTotal_links(entities.size());
		return response;
	}
	
	public static <E extends Enum<E>> E lookup(Class<E> e, String id) {
		E result = null;
		try {
			result = Enum.valueOf(e, id);
		} catch (IllegalArgumentException exception) {
			System.err.println("Invalid status:"+ exception.getMessage());
		}

		return result;
	}
	
}
