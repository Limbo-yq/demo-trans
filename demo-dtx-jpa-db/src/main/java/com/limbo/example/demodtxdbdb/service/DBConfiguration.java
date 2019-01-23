package com.limbo.example.demodtxdbdb.service;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DBConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.ds.user")
    public DataSourceProperties userDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource userDataSource(){
        return userDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public JdbcTemplate userJdbcTemplate(@Qualifier("userDataSource") DataSource userDataSource){
        return new JdbcTemplate(userDataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager userTM = new DataSourceTransactionManager(userDataSource());
        JpaTransactionManager orderTM = new JpaTransactionManager();
        orderTM.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        ChainedTransactionManager tm = new ChainedTransactionManager(userTM, orderTM);
        return tm;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.ds.order")
    public DataSourceProperties orderDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource orderDataSource(){
        return orderDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setDataSource(orderDataSource());
        factory.setPackagesToScan("com.limbo.example.model");
        return factory;
    }
}
