package com.h3c.cloudoc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by lisijie on 2017/5/19.
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "alertDataSource")
    @Qualifier("alertDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.alert")
    public DataSource alertDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "cmdbDataSource")
    @Qualifier("cmdbDataSource")
    @ConfigurationProperties(prefix="spring.datasource.cmdb")
    public DataSource cmdbDataSource() {
        return DataSourceBuilder.create().build();
    }
}
