package com.gdei.hsearch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration//声明这是一个配置类
@EnableJpaRepositories(basePackages = "com.gdei.hsearch.repository") //配置要扫描的JPA包
@EnableTransactionManagement //开启事务管理
public class JPAConfig {
    @Bean//类似于在xml文件中注册一个Bean，即把Bean交给Spring容器管理
    @ConfigurationProperties(prefix = "spring.datasource")//SpringBoot会把值赋值到Bean对应的属性中
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    //创建实体类的管理工厂
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        //我们设置基于Hibernate实现的JPA
        HibernateJpaVendorAdapter japVendor = new HibernateJpaVendorAdapter();
        japVendor.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(japVendor);
        entityManagerFactory.setPackagesToScan("com.gdei.hsearch.entity");
        return entityManagerFactory;
    }

    //注册一个事务管理类对象
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}

