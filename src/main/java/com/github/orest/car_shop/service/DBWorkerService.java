package com.github.orest.car_shop.service;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

import java.sql.*;

@AllArgsConstructor
@Data
@Configuration
@ComponentScan(basePackages = { "com.github.orest.*" })
@PropertySource("classpath:application.properties")
public class DBWorkerService {

    @Value("${db.url}") private String url;
    @Value("${db.login}")
    private String login;
    @Value("${db.password}")
    private String password;

    private Statement statement;

    public DBWorkerService() throws SQLException {
        System.out.println(url);
        try (Connection connection = DriverManager.getConnection(url, login, password);) {
            statement = connection.createStatement();
            System.out.println(connection.isClosed());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
    }

    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    //    try {
    //        Driver driver = new com.mysql.cj.jdbc.Driver();
    //        DriverManager.registerDriver(driver);
    //    } catch (SQLException e) {
    //        e.printStackTrace();
    //    }

 }
