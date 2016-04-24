package com.grass.config;

import com.grass.module.shiro.realm.GrassRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置文件
 * Created by yahui on 2016/4/24.
 */
@Configuration
@ConfigurationProperties(prefix = "shiro.")
public class ShiroConfig {
    @Value("path.map")
    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
    @Value("session.sessionTimeOut")
    private long sessionTimeOut;
    @Value("session.sessionValidationInterval")
    private long sessionValidationInterval;
    @Value("session.sessionValidationSchedulerEnabled")
    private boolean sessionValidationSchedulerEnabled;
    @Value("session.sessionIdCookieEnabled")
    private boolean sessionIdCookieEnabled;
    @Value("cookie.name")
    private String cookieName;
    @Value("ehcache.configFilePath")
    private String ehcacheConfigFilePath;
    @Value("path.loginUrl")
    private String loginPath;
    @Value("path.successUrl")
    private String successPath;

    @Bean
    public GrassRealm grassRealm() {
        return new GrassRealm();
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile(ehcacheConfigFilePath);
        return em;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(grassRealm());
        dwsm.setCacheManager(ehCacheManager());
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean
                .setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl(loginPath);
        shiroFilterFactoryBean.setSuccessUrl(successPath);
        shiroFilterFactoryBean
                .setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SimpleCookie simpleIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName(cookieName);
        return simpleCookie;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//      会话超时时间，单位：毫秒
        sessionManager.setGlobalSessionTimeout(sessionTimeOut);
//        定时清理失效会话, 清理用户直接关闭浏览器造成的孤立会话
        sessionManager.setSessionValidationInterval(sessionValidationInterval);
        sessionManager.setSessionValidationSchedulerEnabled(sessionValidationSchedulerEnabled);
        sessionManager.setSessionIdCookie(simpleIdCookie());
        sessionManager.setSessionIdCookieEnabled(sessionIdCookieEnabled);
        return sessionManager;
    }

}
