package com.config;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.SharedCacheMode;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
*@see SpringJPA javaconfig 
*@author eric
*@version 
*/
@Configuration
@EnableJpaRepositories("com.hibernate.dao")
@EnableTransactionManagement
@ComponentScan("com.spring.service")
@PropertySource("classpath:dbsource.properties")
public class SpringJPA  {
    public SpringJPA () {}
    @Autowired
    Environment env; 

    //Tomcat JDBC Pool
    @Bean(destroyMethod="close")
    public DataSource dataSource(){
        DataSource datasource = new DataSource();
        datasource.setDriverClassName(env.getProperty("db.Driver"));
        datasource.setUrl(env.getProperty("db.Url"));
        datasource.setUsername(env.getProperty("db.Username"));
        datasource.setPassword(env.getProperty("db.Password"));
        datasource.setMaxIdle(Integer.parseInt(env.getProperty("db.MaxIdle")));
        datasource.setMinIdle(Integer.parseInt(env.getProperty("db.MinIdle")));
        return datasource;
    }

    //实例管理器，继承了Hibernate，替代了Hibernate的SessionFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
    
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        factory.setDataSource(dataSource());

        //包扫描，参数可以是一个数组,或者多个字符串
        factory.setPackagesToScan("com.hibernate.model");

        //设置hibernate属性
        Map<String,String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL57InnoDBDialect");
        factory.setJpaPropertyMap(jpaProperties);
        factory.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
        return factory;
      }
    
      //事务管理器
      @Bean
      public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setDataSource(dataSource());
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
      }

}