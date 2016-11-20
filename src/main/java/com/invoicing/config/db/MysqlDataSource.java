package com.invoicing.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Profile("mysql")
@Configuration
@Deprecated
class MysqlDataSource {

/*
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/invoicing");
        dataSource.setUsername("root");
        dataSource.setPassword("changeme01");

        return dataSource;
    }
*/

}