package com.grass.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Configuration
@ComponentScan(basePackages = "com.grass.module", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
