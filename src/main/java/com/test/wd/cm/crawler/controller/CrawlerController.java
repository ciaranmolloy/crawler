package com.test.wd.cm.crawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.wd.cm.crawler.service.CrawlerService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

@RestController
@RequestMapping("/test")
public class CrawlerController {
	
	@Autowired
	private CrawlerService service;

	@RequestMapping(value = "/crawl", method = GET)
	public List<String> crawlWipro() {
		
		return service.getHrefLinks();
	}

}
