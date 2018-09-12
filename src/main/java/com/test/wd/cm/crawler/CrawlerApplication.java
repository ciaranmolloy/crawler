package com.test.wd.cm.crawler;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import redis.embedded.RedisServer;

@SpringBootApplication
public class CrawlerApplication {

	private static RedisServer redisServer;

	public static void main(String[] args) {

		SpringApplication.run(CrawlerApplication.class, args);
	}

	@PostConstruct
	public void startRedisServer() {
		try {
			redisServer = new RedisServer(6379);

			redisServer.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PreDestroy
	public void cleanUp() throws Exception {
		redisServer.stop();
	}
}
