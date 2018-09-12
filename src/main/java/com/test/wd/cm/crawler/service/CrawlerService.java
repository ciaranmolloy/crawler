package com.test.wd.cm.crawler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private static final String HREF = "href";
	private static final String ANCHOR_HREF = "a[href]";

	@Autowired
	private RedisUrlIndex redisUrlIndex;

	public List<String> getHrefLinks(final String url) {
		List<String> strings = Collections.EMPTY_LIST;
		try {
			final Document doc = Jsoup.connect(url).get();
			final Elements links = doc.select(ANCHOR_HREF);
			strings = createLinksList(url, links);

			final UrlIndex index = new UrlIndex(url, strings);
			redisUrlIndex.save(index);

			for (final String s : strings) {
				if (!redisUrlIndex.existsById(url)) {
					getHrefLinks(url);
				}
			}

		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return strings;
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
