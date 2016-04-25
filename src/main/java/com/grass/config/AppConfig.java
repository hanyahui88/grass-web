package com.grass.config;

import com.grass.common.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Configuration
@ComponentScan(basePackages = "com.grass", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Order(3)
public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
    @Bean
    public LocalValidatorFactoryBean validator() {
        logger.info("grass-----AppConfig-----validator-------------init");
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        logger.info("grass-----AppConfig-----springContextHolder-------------init");
        return new SpringContextHolder();
    }
}
