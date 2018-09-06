package com.test.wd.cm.crawler.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.test.wd.cm.crawler.service.CrawlerService;

@RunWith(MockitoJUnitRunner.class)
public class CrawlerControllerTest {
	
	@Mock
	private CrawlerService service;
	
	@InjectMocks
	private CrawlerController controller;
	
	private List<String> strings;
	
	@Before
	public void setup() {
		strings = new ArrayList();
		strings.add("s1");
		strings.add("s2");
		strings.add("s3");
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testGetLinksSuccessful() {
		when(service.getHrefLinks()).thenReturn(strings);
		
		final List<String> response = controller.crawlWipro();
		
		assertNotNull(strings);
		assertEquals(3, response.size());
		
		verify(service).getHrefLinks();
	}
	
	
}
