package com.dsb.util;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EhCacheServiceImpl implements EhCacheService {

	@Override
	@Cacheable(value = "cacheTest", key = "#param")
	public String getTimestamp(String param) {
		Long timestamp = System.currentTimeMillis();
		return timestamp.toString();
	}

}
