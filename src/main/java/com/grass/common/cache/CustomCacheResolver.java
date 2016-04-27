package com.grass.common.cache;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by 韩亚辉 on 2016/4/16.
 */
public class CustomCacheResolver implements CacheResolver {
    @Autowired
    private CacheManager cacheManager;

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        List<Cache> caches = Lists.newArrayList();
        Set<String> cacheNames = context.getOperation().getCacheNames();
        for (String cacheName : cacheNames) {
            caches.add(cacheManager.getCache(cacheName));
        }
        return caches;
    }
}
