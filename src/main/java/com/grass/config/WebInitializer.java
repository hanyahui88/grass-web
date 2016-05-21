package com.grass.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.grass.common.filter.CommonFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
        logger.info("grass-----WebInitializer-----getServletMappings-------------init");
        return new String[]{"/"};
    }

    /*
      * 应用上下文，除web部分
	  */
    @Override
    protected Class[] getRootConfigClasses() {
        logger.info("grass-----WebInitializer-----getRootConfigClasses-------------init");
        return new Class[]{};
    }

    /*
      * web上下文
	  */
    @Override
    protected Class[] getServletConfigClasses() {
        logger.info("grass-----WebInitializer-----getServletConfigClasses-------------init");
        return new Class[]{};
    }

    /*
      * 注册过滤器，映射路径与DispatcherServlet一致，路径不一致的过滤器需要注册到另外的WebApplicationInitializer中
	  */
    @Override
    protected Filter[] getServletFilters() {
        logger.info("grass-----WebInitializer-----getServletFilters-------------init");
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        CommonFilter commonFilter = new CommonFilter();
        return new Filter[]{characterEncodingFilter, commonFilter};
    }

    /*
      * 可以注册DispatcherServlet的初始化参数，等等
	  */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("grass-----WebInitializer-----onStartup-------------init");
        ServletRegistration.Dynamic druidStatView = servletContext.addServlet("DruidStatView", StatViewServlet.class);
        druidStatView.addMapping("/druid/*");

    }
}
