package com.grass.config;

import com.grass.common.utils.SpringContextHolder;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by 韩亚辉 on 2016/4/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest({"server.port=8888", "management.port=0"})
public class TestConfig {
    @Test
    public void shiroConfigTest() {
        Object bean= SpringContextHolder.getBean("shiroFilter");
//        Map<String, String> chainMap = factoryBean.getFilterChainDefinitionMap();
//        String loginUrl = factoryBean.getLoginUrl();
//        String successUrl = factoryBean.getSuccessUrl();
//        String unauthorizedUrl = factoryBean.getUnauthorizedUrl();
        System.out.println("");
    }
}
