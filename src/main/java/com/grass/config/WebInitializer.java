package com.grass.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /*
     * DispatcherServlet的映射路径
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /*
      * 应用上下文，除web部分
	  */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected Class[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /*
      * web上下文
	  */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected Class[] getServletConfigClasses() {
        return new Class[]{MvcConfig.class};
    }

    /*
	  * 注册过滤器，映射路径与DispatcherServlet一致，路径不一致的过滤器需要注册到另外的WebApplicationInitializer中
	  */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{characterEncodingFilter};
    }

    /*
	  * 可以注册DispatcherServlet的初始化参数，等等
	  */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        //registration.setInitParameter("spring.profiles.active", "default");

    }
//
//    /*
//     * 创建一个可以在非spring管理bean中获得spring管理的相关bean的对象：CP_SpringContext.getBean(String beanName)
//     */
//    @Override
//    protected WebApplicationContext createRootApplicationContext() {
//        // TODO Auto-generated method stub
//        WebApplicationContext ctx = super.createRootApplicationContext();
//        CP_SpringContext.getInstance().setApplicationContext(ctx);
//        return ctx;
//    }
}
