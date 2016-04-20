package com.grass.common.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * Created by 韩亚辉 on 2016/4/16.
 */
public class CustomCacheErronrHandler implements CacheErrorHandler {
    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        System.out.println("cache get error");
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        System.out.println("cache put error");
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        System.out.println("cache evict error");
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        System.out.println("cache clear error");
    }
}
