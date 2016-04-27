package com.grass.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grass.common.cache.CustomCacheErrorHandler;
import com.grass.common.cache.CustomCacheResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by 韩亚辉 on 2016/4/20.
 */
@Configuration
@EnableCaching
@Order(1)
@ConfigurationProperties(prefix = "spring.redis.")
public class CachingConfig extends CachingConfigurerSupport {

    @Value("${keyPrefix}")
    private String keyPrefix;
    @Value("${hostName}")
    private String hostName;
    @Value("${port}")
    private int port;
    @Value("${password}")
    private String password;
    @Value("${timeout}")
    private int timeout;
    @Value("${database}")
    private int database;
    //最大能够保持idel状态的对象数
    @Value("${pool.maxIdle}")
    private int maxIdle;
    //最大分配的对象数
    @Value("${pool.maxTotal}")
    private int maxTotal;
    //当调用borrow Object方法时，是否进行有效性检查
    @Value("${pool.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${enableTransactionSupport}")
    private boolean enableTransactionSupport;
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
        return this.redisCacheManager();
    }

    @Override
    public KeyGenerator keyGenerator() {
        logger.info("grass-----CachingConfig-----keyGenerator-------------init");
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    @Override
    public CacheErrorHandler errorHandler() {
        logger.info("grass-----CachingConfig-----errorHandler-------------init");
        return new CustomCacheErrorHandler();
    }

    //-----------------redis config------------------------------
    @Bean
    public RedisCacheManager redisCacheManager() {
        logger.info("grass-----CachingConfig-----redisCacheManager-------------init");
        return new RedisCacheManager(this.redisTemplate());
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        logger.info("grass-----CachingConfig-----jedisConnectionFactory-------------init");
        JedisConnectionFactory jedisConnectionFactory =
                new JedisConnectionFactory();
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        logger.info("grass-----CachingConfig-----redisTemplate-------------init");
        StringRedisTemplate template = new StringRedisTemplate(this.redisConnectionFactory());
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setConnectionFactory(this.redisConnectionFactory());
        template.afterPropertiesSet();
        template.setConnectionFactory(jedisConnectionFactory());
//        template.setEnableTransactionSupport(this.enableTransactionSupport);
        return template;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(this.maxIdle);
        jedisPoolConfig.setMaxTotal(this.maxTotal);
        jedisPoolConfig.setTestOnBorrow(this.testOnBorrow);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool() {
        JedisPool jedisPool = new JedisPool(jedisPoolConfig(), this.hostName, this.port, this.timeout, this.password, this.database);
        return jedisPool;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        logger.info("grass-----CachingConfig-----jedisConnectionFactory-------------init");
        logger.info("grass-----CachingConfig-----jedisConnectionFactory-------------hostname:" + this.keyPrefix);
        logger.info("grass-----CachingConfig-----jedisConnectionFactory-------------hostname:" + this.hostName);
        logger.info("grass-----CachingConfig-----jedisConnectionFactory-------------hostname:" + this.port);
        logger.info("grass-----CachingConfig-----jedisConnectionFactory-------------hostname:" + this.password);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(this.hostName);
        jedisConnectionFactory.setPort(this.port);
        jedisConnectionFactory.setDatabase(this.database);
        jedisConnectionFactory.setPassword(this.password);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
        return new JedisConnectionFactory();
    }

}
