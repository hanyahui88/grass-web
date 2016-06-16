package com.grass.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * mongodb 的配置
 * Created by Duo Nuo on 2016/6/13.
 */
//@Configuration
//@ConfigurationProperties(prefix = "spring.mongodb")
public class MongoConfig extends AbstractMongoConfiguration {
    @Value("${host}")
    private String host;

    @Value("${port}")
    private String port;

    @Value("${database}")
    private String database;

    @Override
    public MongoMappingContext mongoMappingContext()
            throws ClassNotFoundException {
        // TODO Auto-generated method stub
        return super.mongoMappingContext();
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(host + ":" + port);
    }

    @Override
    protected String getDatabaseName() {
        // TODO Auto-generated method stub
        return database;
    }
}
