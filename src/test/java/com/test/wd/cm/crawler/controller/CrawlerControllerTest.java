package com.test.wd.cm.crawler.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.test.wd.cm.crawler.service.CrawlerService;

@RunWith(MockitoJUnitRunner.class)
public class CrawlerControllerTest {
	
	@Mock
	private CrawlerService service;
	
	@InjectMocks
	private CrawlerController controller;
	
	private Map testMap = new HashMap();
	
	@Before
	public void setup() {
		testMap.put("url1", new ArrayList());
		testMap.put("url2", new ArrayList());
		testMap.put("url3", new ArrayList());
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testGetLinksSuccessful() {
		when(service.getHrefLinks("https://url")).thenReturn(testMap);
		
		final Map response = controller.crawlWipro("url");
		
		assertNotNull(response);
		assertEquals(3, response.size());
		
		verify(service).getHrefLinks("https://url");
	}
	
	
}
