package com.test.wd.cm.crawler.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class CrawlerServiceTest {
	
	@InjectMocks
	private CrawlerService service;
	
	@Before
	public void setup() {
		ReflectionTestUtils.setField(service, "url", "http://localhost:8080/");
	}
	
	@Test
	public void testGetHrefLinksSuccessful() {
		final List response = service.getHrefLinks();
		
		assertNotNull(response);	
	}
}
