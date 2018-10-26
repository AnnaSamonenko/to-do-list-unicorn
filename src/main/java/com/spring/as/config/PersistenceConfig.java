package com.spring.as.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.spring.as.entity"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-75-251-84.eu-west-1.compute.amazonaws.com:5432/d3rko5fne6tdgp?user=yvgomivwtreysy&password=949467df493118706ad2c98cceb90a7592fd7aee355c7cd28dce18d87bbb8757&sslmode=require");
        dataSource.setUsername("yvgomivwtreysy");
        dataSource.setPassword("949467df493118706ad2c98cceb90a7592fd7aee355c7cd28dce18d87bbb8757");
        return dataSource;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("spring.jpa.show-sql", "false");
        properties.setProperty("spring.jpa.generate-ddl", "true");
        properties.setProperty("spring.jpa.hibernate.ddl-auto", "create");
        return properties;
    }
}