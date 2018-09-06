package com.test.wd.cm.crawler.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.wd.cm.crawler.service.CrawlerService;
import com.test.wd.cm.crawler.service.impl.CrawlerServiceImpl;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/test")
public class CrawlerController {
	
	// TODO - Create Bean and Autowire
	private final CrawlerServiceImpl crawlerServiceImpl = new CrawlerServiceImpl();
	private CrawlerService crawlerService;

	@RequestMapping(value = "/crawl", method = GET)
	public String crawlWipro() {
		crawlerService = crawlerServiceImpl.getService();
		
		final String html = crawlerService.getHTML();
		
		return html;
	}

}
