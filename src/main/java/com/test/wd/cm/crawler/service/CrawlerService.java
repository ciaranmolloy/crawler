package com.test.wd.cm.crawler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CrawlerService {
	private static final String HREF = "href";
	private static final String ANCHOR_HREF = "a[href]";

	@Value("${wipro.url}")
	private String url;

	public List<String> getHrefLinks() {

		Document doc;
		Elements links;
		StringBuilder string = null;
		List strings = Collections.EMPTY_LIST;

		try {
			doc = Jsoup.connect(url).get();
			links = doc.select(ANCHOR_HREF);
			string = new StringBuilder();

			strings = createLinksList(links, string);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return strings;
	}

	private List<String> createLinksList(final Elements links, final StringBuilder string) {
		final List<String> strings = new ArrayList();
		for (final Element link : links) {
			final String linkUrl = link.attr(HREF);

			if (linkUrl.contains(url)) {
				string.append("-- ").append(linkUrl).append("\n");
				strings.add(linkUrl);
			}
		}
		return strings;
	}
}
