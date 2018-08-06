package com.springboot.actorsservice.config;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "spring.testdatasource")
public class TestDataConfig {
    @Value("${hibernate.dialect}")
    String dialect;

    @Bean(name = "datasource")
//    @ConfigurationProperties(prefix = "spring.testdatasource")
    public DataSource testDatasource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(@Qualifier("datasource") DataSource dataSource) {

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);


        LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localEntityManagerFactoryBean.setDataSource(dataSource);
        localEntityManagerFactoryBean.setPackagesToScan("com.springboot.actorsservice.model");
        localEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localEntityManagerFactoryBean.setJpaProperties(properties);
        return localEntityManagerFactoryBean;
    }
}
