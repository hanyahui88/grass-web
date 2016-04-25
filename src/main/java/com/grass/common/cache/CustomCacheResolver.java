package com.grass.common.cache;

import com.google.common.collect.Lists;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

import java.util.Collection;
import java.util.List;

/**
 * Created by 韩亚辉 on 2016/4/16.
 */
public class CustomCacheResolver implements CacheResolver {
    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        List<Cache> caches = Lists.newArrayList();
        return caches;
    }
}
