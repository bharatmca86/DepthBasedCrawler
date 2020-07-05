package com.deutshe.crawler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deutshe.crawler.repository.dto.CrawledEntity;
/**
 * 
 * @author bharat
 *
 */
public interface CrawlerRepository extends JpaRepository<CrawledEntity, Integer>{

	@Query
	public List<CrawledEntity> findByStatus(@Param("status") int status);
}
