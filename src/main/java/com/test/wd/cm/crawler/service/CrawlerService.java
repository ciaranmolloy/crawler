package com.test.wd.cm.crawler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CrawlerService {
	private static final String URL_LINKS_MAP = "urlLinksMap";
	private static final int SECONDS_10 = 1000 * 10;
	private static final String REFERRER = "http://www.google.com";
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0";
	private static final String HREF = "href";
	private static final String ANCHOR_HREF = "a[href]";
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public Map getHrefLinks(final String url) {
		if (!redisTemplate.opsForHash().entries(URL_LINKS_MAP).containsKey(url)) {
			mapUrlLinks(url);
		}
		return redisTemplate.opsForHash().entries(URL_LINKS_MAP);
	}

	private void mapUrlLinks(final String url) {
		
		try {
			
			final Document doc = Jsoup.connect(url)
					.userAgent(USER_AGENT)
					.referrer(REFERRER)
					.timeout(SECONDS_10)
					.ignoreHttpErrors(true)
					.get();

			final Elements links = doc.select(ANCHOR_HREF);
			final List<String> linksList = createLinksList(url, links);

			redisTemplate.opsForHash().put(URL_LINKS_MAP, url, linksList);

			for (final String nextUrl : linksList) {
				getHrefLinks(nextUrl);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> createLinksList(final String url, final Elements links) {
		final List<String> strings = new ArrayList<String>();
		for (final Element link : links) {
			final String linkUrl = link.attr(HREF);

			if (linkUrl.contains(url)) {
				strings.add(linkUrl);
			}
		}
		return strings;
	}
}
