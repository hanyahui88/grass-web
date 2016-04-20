package com.grass.config;

import com.alibaba.druid.support.http.StatViewServlet;
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
        return new String[]{"/"};
    }

    /*
      * 应用上下文，除web部分
	  */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected Class[] getRootConfigClasses() {
        return new Class[]{};
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

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.err.print("--------------------开始加载WebApplicationConfig-----------------------");
        ServletRegistration.Dynamic druidStatView = servletContext.addServlet("DruidStatView", StatViewServlet.class);
        druidStatView.addMapping("/druid/*");
        System.err.print("--------------------结束加载WebApplicationConfig-----------------------");
    }
}
