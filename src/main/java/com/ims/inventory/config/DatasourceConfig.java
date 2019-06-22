package com.ims.inventory.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Value("classpath:sqls/oauth2sql.sql")
    private Resource schemaScript;

    @Bean
    public DataSource jdbcDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("Osmali@1234");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/inventory");
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource jdbcDataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(jdbcDataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        return populator;
    }


}
