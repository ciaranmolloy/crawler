package com.test.wd.cm.crawler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.test.wd.cm.crawler.model.UrlIndex;
import com.test.wd.cm.crawler.redis.RedisUrlIndex;

@Component
public class CrawlerService {
	private static final int SECONDS_10 = 1000 * 10;
	private static final String REFERRER = "http://www.google.com";
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0";
	private static final String HREF = "href";
	private static final String ANCHOR_HREF = "a[href]";

	private Map<String, List<String>> map = new HashMap<String, List<String>>();

	@Autowired
	private RedisUrlIndex redisUrlIndex;

	public Map getHrefLinks(final String url) {
		if (!map.containsKey(url)) {
			mapUrlLinks(url);
		}
		return map;
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

			final List<String> strings = createLinksList(url, links);

			map.put(url, strings);

			for (final String nextUrl : strings) {
				getHrefLinks(nextUrl);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<String> createLinksList(final String url, final Elements links) {
		final List<String> strings = new ArrayList();
		for (final Element link : links) {
			final String linkUrl = link.attr(HREF);

			if (linkUrl.contains(url)) {
				strings.add(linkUrl);
			}
		}
		return strings;
	}
}
