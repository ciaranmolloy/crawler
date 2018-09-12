package com.test.wd.cm.crawler.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("UrlIndex")
public class UrlIndex implements Serializable {
	
	/**
	 * serialVersionUID used for de-serialization
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private List<String> urlList;
	
	public UrlIndex(final String id, final List<String> urlList) {
		this.id = id;
		this.urlList = urlList;
	}

	public void setUrlList(final List<String> urlList) {
		this.urlList = urlList;
	}
	
	public List<String> getUrlList() {
		return this.urlList;
	}

}
