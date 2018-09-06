package com.test.wd.cm.crawler.service.impl;

import com.test.wd.cm.crawler.service.CrawlerService;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class CrawlerServiceImpl {

	private static final String WIPRO = "https://wiprodigital.com/";

	public CrawlerService getService() {
		return Feign.builder()
				.client(new OkHttpClient())
				.encoder(new GsonEncoder())
				.logger(new Slf4jLogger(CrawlerServiceImpl.class))
				.target(CrawlerService.class, WIPRO);
	}
}
