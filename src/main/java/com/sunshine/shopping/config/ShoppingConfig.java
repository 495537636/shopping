/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: config
 * @Prject: shopping
 * @Package: com.sunshine.shopping
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/6/26 19:43
 * @version: V1.0
 */

package com.sunshine.shopping.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

/**
 * @Title: config
 * @Description: 主配置信息类
 * @author LiMG
 * @date 2017/6/26 19:43
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.sunshine.shopping")
public class ShoppingConfig implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 配置数据源
     * @return
     */
    @Bean(name = "dataSource")
    public DruidDataSource getDataSource() {
        try {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/shopping?useSSL=true&useUnicode=true&characterEncoding=UTF-8");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            return dataSource;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 配置sqlSessionFactory
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean getSqlSessionFactory() {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //扫描指定路径下的mapper文件
            Resource[] resources = resolver.getResources("classpath:com/sunshine/shopping/mapper/*.xml");
            sessionFactoryBean.setMapperLocations(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactoryBean;
    }

    /**
     * 配置Mapper接口所在包名，Spring会自动查找其下的类
     */
    @Bean
    public MapperScannerConfigurer settingMapperScannerConfigurer() {
        MapperScannerConfigurer mapperConfigurer = new MapperScannerConfigurer();
        mapperConfigurer.setBasePackage("com.sunshine.shopping.mapper");
        mapperConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperConfigurer;
    }

    /**
     * 配置事务管理
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getDataSourceTransactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(getDataSource());
        return manager;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
