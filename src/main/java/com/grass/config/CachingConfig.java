package com.grass.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.grass.common.cache.CustomCacheErronrHandler;
import com.grass.common.cache.CustomCacheResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by 韩亚辉 on 2016/4/20.
 */
@Configuration
@EnableCaching
@Order(1)
public class CachingConfig extends CachingConfigurerSupport {
    /**
     * 日志对象
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    // --------------  Cache Cofig --------------
    @Bean
    @Override
    public CacheResolver cacheResolver() {
        logger.info("grass-----CachingConfig-----cacheResolver-------------init");
        return new CustomCacheResolver();
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        logger.info("grass-----CachingConfig-----cacheManager-------------init");
//        Set<CacheManager> caches =Sets.newLinkedHashSet();
//        caches.add(ehCacheCacheManager());
//        caches.add(redisCacheManager());
//        CompositeCacheManager compositeCacheManager = new CompositeCacheManager();
//        compositeCacheManager.setCacheManagers(caches);
//        compositeCacheManager.setFallbackToNoOpCache(true);
        return redisCacheManager();
    }

    @Override
    public KeyGenerator keyGenerator() {
        logger.info("grass-----CachingConfig-----keyGenerator-------------init");
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        logger.info("grass-----CachingConfig-----errorHandler-------------init");
        return new CustomCacheErronrHandler();
    }

    // --------------  EhCache config --------------
//    @Bean
//    public EhCacheCacheManager ehCacheCacheManager() {
//        logger.info("--------------------开始加载-ehCacheCacheManager-----------------------");
//        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
//    }
//
//    @Bean
//    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
//        logger.info("--------------------开始加载-ehCacheManagerFactoryBean-----------------------");
//        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
//        factoryBean.setConfigLocation(new ClassPathResource("ehcache-shiro.xml"));
//        factoryBean.setShared(true);
//        return factoryBean;
//    }

    //-----------------redis config------------------------------
    @Bean
    public RedisCacheManager redisCacheManager() {
        logger.info("grass-----CachingConfig-----redisCacheManager-------------init");
        return new RedisCacheManager(redisTemplate());
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        logger.info("grass-----CachingConfig-----jedisConnectionFactory-------------init");
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        logger.info("grass-----CachingConfig-----redisTemplate-------------init");
        StringRedisTemplate template = new StringRedisTemplate(jedisConnectionFactory());
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
