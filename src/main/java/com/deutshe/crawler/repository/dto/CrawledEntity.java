package com.deutshe.crawler.repository.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="CRAWLED")
@NamedQuery(name="CrawledEntity.findAll", query="SELECT c FROM CrawledEntity c")
public class CrawledEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7212371696880629752L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="PAGE_TITLE", length=256)
	private String pageTitle;
	
	@Column(name="URL", length=256)
	private String url;
	
	@Column(name="IMAGE_COUNT", length=10)
	private int imageCount;
	
	@Column(name="STATUS", length=1)
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public int getImageCount() {
		return imageCount;
	}
	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}
	
	
	
}
