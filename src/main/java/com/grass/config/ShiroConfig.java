//package com.grass.config;
//
//import com.beust.jcommander.internal.Maps;
//import com.grass.module.shiro.realm.SystemAuthorizingRealm;
//import org.apache.shiro.cache.ehcache.EhCacheManager;
//import org.apache.shiro.session.mgt.SessionManager;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.boot.bind.RelaxedPropertyResolver;
//import org.springframework.boot.context.embedded.FilterRegistrationBean;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.core.env.Environment;
//import org.springframework.web.filter.DelegatingFilterProxy;
//
//import java.util.Map;
//
///**
// * shiro 配置文件
// * Created by yahui on 2016/4/24.
// */
//@Configuration
//@Order(5)
//public class ShiroConfig implements EnvironmentAware {
//    private RelaxedPropertyResolver propertyResolver;
//
//    @Override
//    public void setEnvironment(Environment env) {
//        this.propertyResolver = new RelaxedPropertyResolver(env, "shiro.");
//    }
//
//    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
//
//    @Bean
//    public SystemAuthorizingRealm grassRealm() {
//        return new SystemAuthorizingRealm();
//    }
//
//    @Bean
//    public EhCacheManager ehCacheManager() {
//        logger.info("grass-----ShiroConfig-----ehCacheManager-------------init");
//        EhCacheManager em = new EhCacheManager();
//        em.setCacheManagerConfigFile(propertyResolver.getProperty("ehcache.configFilePath"));
//        return em;
//    }
//
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        logger.info("grass-----ShiroConfig-----lifecycleBeanPostProcessor-------------init");
//        return new LifecycleBeanPostProcessor();
//    }
//    @Bean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        logger.info("grass-----ShiroConfig-----defaultAdvisorAutoProxyCreator-------------init");
//        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
//        daap.setProxyTargetClass(true);
//        return daap;
//    }
//
//    @Bean
//    public DefaultWebSecurityManager securityManager() {
//        logger.info("grass-----ShiroConfig-----securityManager-------------init");
//        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
//        dwsm.setRealm(grassRealm());
//        dwsm.setSessionManager(sessionManager());
//        dwsm.setCacheManager(ehCacheManager());
////        dwsm.setSubjectFactory(new DefaultSubjectFactory());
////        整合cas设置
////        dwsm.setRealm(GrassCasRealm());
////        dwsm.setSubjectFactory(new CasSubjectFactory());
//        return dwsm;
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
//        logger.info("grass-----ShiroConfig-----authorizationAttributeSourceAdvisor-------------init");
//        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
//        aasa.setSecurityManager(securityManager());
//        return aasa;
//    }
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilter() {
//        logger.info("grass-----ShiroConfig-----shiroFilter-------------init");
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean
//                .setSecurityManager(securityManager());
//        shiroFilterFactoryBean.setLoginUrl(propertyResolver.getProperty("path.loginUrl"));
//        shiroFilterFactoryBean.setSuccessUrl(propertyResolver.getProperty("path.successUrl"));
//        shiroFilterFactoryBean.setUnauthorizedUrl(propertyResolver.getProperty("path.successUrl"));
//        //获取map是有问题，会给每个键之前加一个"."，所以把点去掉
//        Map<String, Object> map = propertyResolver.getSubProperties("path.map");
////       整合cas配置
////        Map<String, Filter> filters = Maps.newHashMap();
////        filters.put("casFilter", casFilter());
////        shiroFilterFactoryBean.setFilters(filters);
//        Map<String, String> chainMap = Maps.newHashMap();
//        for (String item : map.keySet()) {
//            chainMap.put(item.substring(1), map.get(item) + "");
//        }
//        shiroFilterFactoryBean
//                .setFilterChainDefinitionMap(chainMap);
//        return shiroFilterFactoryBean;
//    }
//
//    @Bean
//    public SimpleCookie simpleIdCookie() {
//        SimpleCookie simpleCookie = new SimpleCookie();
//        logger.info("grass-----ShiroConfig-----simpleIdCookie-------------init");
//        simpleCookie.setName(propertyResolver.getProperty("cookie.name"));
//        return simpleCookie;
//    }
//
//    @Bean
//    public SessionManager sessionManager() {
//        logger.info("grass-----ShiroConfig-----sessionManager-------------init");
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
////      会话超时时间，单位：毫秒
//        sessionManager.setGlobalSessionTimeout(propertyResolver.getProperty("session.sessionTimeOut", Long.class));
////        定时清理失效会话, 清理用户直接关闭浏览器造成的孤立会话
//        sessionManager.setSessionValidationInterval(propertyResolver.getProperty("session.sessionValidationInterval", Long.class));
//        sessionManager.setSessionValidationSchedulerEnabled(propertyResolver.getProperty("session.sessionValidationSchedulerEnabled", Boolean.class));
//        sessionManager.setSessionIdCookie(simpleIdCookie());
//        sessionManager.setSessionIdCookieEnabled(propertyResolver.getProperty("session.sessionIdCookieEnabled", Boolean.class));
//        return sessionManager;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
//        filterRegistration.addInitParameter("targetFilterLifecycle", "false");
//        filterRegistration.setEnabled(true);
//        filterRegistration.addUrlPatterns("/*");
//        return filterRegistration;
//    }
//
//    ////////////////////////////////////////////////整合cas/////////////////////////////////////////////////////////////////
//
////    @Bean
////    public GrassCasRealm GrassCasRealm() {
////        GrassCasRealm realm = new GrassCasRealm();
////        realm.setCacheManager(ehCacheManager());
////        return realm;
////    }
////    /**
////     * CAS过滤器
////     *
////     * @return
////     * @author hanyahui
////     * @create 2016年1月17日
////     */
////    @Bean(name = "casFilter")
////    public CasFilter casFilter() {
////        CasFilter casFilter = new CasFilter();
////        casFilter.setName("casFilter");
////        casFilter.setEnabled(true);
////        // 登录失败后跳转的URL，也就是 Shiro 执行 CasRealm 的 doGetAuthenticationInfo 方法向CasServer验证tiket
////        casFilter.setFailureUrl(propertyResolver.getProperty("path.failureUrl"));// 我们选择认证失败后再打开登录页面
////        return casFilter;
////    }
//}
