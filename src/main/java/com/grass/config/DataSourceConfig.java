package com.grass.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.grass.module")
@Order(2)
public class DataSourceConfig implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Override
    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.");
    }

    @Bean
    public DataSource dataSource() {
        logger.info("grass-----DataSourceConfig-----dataSource-------------init");
        logger.info("grass-----DataSourceConfig-----dataSource-------------url:"+propertyResolver.getProperty("datasource.url"));
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(propertyResolver.getProperty("datasource.url"));
        dataSource.setDriverClassName(propertyResolver.getProperty("datasource.driverClassName"));
        dataSource.setUsername(propertyResolver.getProperty("datasource.username"));
        dataSource.setPassword(propertyResolver.getProperty("datasource.password"));
        return dataSource;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        logger.info("grass-----DataSourceConfig-----transactionManager-------------init");
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;

    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        logger.info("grass-----DataSourceConfig-----sqlSessionFactory-------------init");
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        Interceptor[] interceptors = new Interceptor[1];
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
//      <!-- 4.0.0以后版本可以不设置该参数 -->
//        <property name="dialect" value="mysql"/>
//        <!-- 该参数默认为false -->
//        <!-- 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用 -->
//        <!-- 和startPage中的pageNum效果一样-->
//        <property name="offsetAsPageNum" value="true"/>
//        <!-- 该参数默认为false -->
//        <!-- 设置为true时，使用RowBounds分页会进行count查询 -->
//        <property name="rowBoundsWithCount" value="true"/>
//        <!-- 设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果 -->
//        <!-- （相当于没有执行分页查询，但是返回结果仍然是Page类型）-->
//        <property name="pageSizeZero" value="true"/>
//        <!-- 3.3.0版本可用 - 分页参数合理化，默认false禁用 -->
//        <!-- 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页 -->
//        <!-- 禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据 -->
//        <property name="reasonable" value="false"/>
//        <!-- 3.5.0版本可用 - 为了支持startPage(Object params)方法 -->
//        <!-- 增加了一个`params`参数来配置参数映射，用于从Map或ServletRequest中取值 -->
//        <!-- 可以配置pageNum,pageSize,count,pageSizeZero,reasonable,orderBy,不配置映射的用默认值 -->
//        <!-- 不理解该含义的前提下，不要随便复制该配置 -->
//        <property name="params" value="pageNum=start;pageSize=limit;"/>
//        <!-- 支持通过Mapper接口参数来传递分页参数 -->
//        <property name="supportMethodsArguments" value="true"/>
//        <!-- always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page -->
//        <property name="returnPageInfo" value="check"/>
        properties.put("dialect", "mysql");
        properties.put("rowBoundsWithCount", true);
        properties.put("pageSizeZero", false);
        properties.put("reasonable", true);
        properties.put("supportMethodsArguments", true);
        properties.put("returnPageInfo", "check");
        pageHelper.setProperties(properties);
        interceptors[0] = pageHelper;
        sessionFactory.setPlugins(interceptors);
        sessionFactory.setTypeAliasesPackage(propertyResolver.getProperty("mybatis.typeAliasesPackage"));
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(propertyResolver.getProperty("mybatis.mapperLocations")));
        return sessionFactory.getObject();
    }

}
