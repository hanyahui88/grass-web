package com.grass.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.grass.module"}, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})})
@ConfigurationProperties(prefix = "spring.mvc.")
@Order(4)
public class MvcConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
    @Value("${prefix}")
    private String prefix;
    @Value("${suffix}")
    private String suffix;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        logger.info("grass-----MvcConfig-----viewResolver-------------init");
        logger.info("grass-----MvcConfig-----viewResolver-------------prefix:"+prefix);
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(prefix);
        resolver.setSuffix(suffix);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        logger.info("grass-----MvcConfig-----addViewControllers-------------init");
        registry.addViewController("/").setViewName("forward:/static/login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("grass-----MvcConfig-----addResourceHandlers-------------init");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/");
    }
}
