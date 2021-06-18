package com.mixley.cloud.common.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BaseConfig {
    @Bean
    @ConditionalOnMissingBean
    public CacheManager cacheManager(){
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        ArrayList<Cache> caches = new ArrayList<Cache>();
        caches.add(new ConcurrentMapCache(""));
        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }
}
