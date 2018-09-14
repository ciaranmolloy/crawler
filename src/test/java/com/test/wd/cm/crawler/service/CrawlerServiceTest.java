package com.test.wd.cm.crawler.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
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
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@RunWith(MockitoJUnitRunner.class)
public class CrawlerServiceTest {
	
	@Mock
	private RedisTemplate<String, Object> redisTemplate;
	
	@Mock
	private HashOperations hashOperations;
	
	private Map testMap = new HashMap();
	
	@InjectMocks
	private CrawlerService service;
	
	@Before
	public void setup() {
		testMap.put("url1", new ArrayList());
		testMap.put("url2", new ArrayList());
		testMap.put("url3", new ArrayList());
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(redisTemplate);
		verifyNoMoreInteractions(hashOperations);
	}
	
	@Test
	public void testGetHrefLinksSuccessful() {
		when(redisTemplate.opsForHash()).thenReturn(hashOperations);
		when(hashOperations.entries(anyString())).thenReturn(testMap);
		
		final Map response = service.getHrefLinks("http://google.com");
		
		assertNotNull(response);
		
		verify(redisTemplate, times(3)).opsForHash();
		verify(hashOperations, times(2)).entries(anyString());
		verify(hashOperations).put(any(), any(), any());
	}
}
