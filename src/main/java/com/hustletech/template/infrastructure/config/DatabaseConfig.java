package com.hustletech.template.infrastructure.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.hustletech.template.domain.repository")
@EnableTransactionManagement
public class DatabaseConfig {

    private final Environment env;

    public DatabaseConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(env.getRequiredProperty("spring.datasource.url"))
                .username(env.getRequiredProperty("spring.datasource.username"))
                .password(env.getRequiredProperty("spring.datasource.password"))
                .driverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"))
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", env.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("spring.jpa.show-sql"));

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("com.hustletech.template.domain.entity");
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaPropertyMap(properties);
        return factory;
    }
}
