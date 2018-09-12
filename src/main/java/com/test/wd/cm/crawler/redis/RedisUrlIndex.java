package com.test.wd.cm.crawler.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.wd.cm.crawler.model.UrlIndex;

@Repository
public interface RedisUrlIndex extends CrudRepository<UrlIndex, String> {

}
