package com.test.wd.cm.crawler.service;

import feign.RequestLine;

/**
 * @author Ciaran
 *
 */
public interface CrawlerService {
	
	@RequestLine("GET /")
	public String getHTML();

}
