package com.github.orest.car_shop.service;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.*;

@Configuration
@ComponentScan(basePackages = "com.github.orest.car_shop")
@PropertySource(value = { "classpath:application.properties" })
@Service("DBWorkerService")
@AllArgsConstructor
@Data
public class DBWorkerService {

    @Value("${db.url}") private String url;
    @Value("${db.login}") private String login;
    @Value("${db.password}") private String password;


    @Value("ggggg") private String x;

    @Autowired
    private Environment environment;

    private Statement statement;

    public DBWorkerService() throws SQLException {

        //System.out.println(url);
        //System.out.println(login);
        //System.out.println(password);
        //jdbc:mysql://localhost:3306/car_shop
        //try {
        //    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_shop", "root", "root");
        //    statement = connection.createStatement();
            //System.out.println(statement.isClosed());
            //System.out.println(connection.isClosed());

        //} catch (SQLException e) {
        //    e.printStackTrace();
       // }
    }

    @Bean
    public Statement connectDB() throws SQLException {

        System.out.println(url);
        System.out.println(login);
        System.out.println(password);
        System.out.println(x);
        //jdbc:mysql://localhost:3306/car_shop
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_shop", "root", "root");
            statement = connection.createStatement();
            //System.out.println(statement.isClosed());
            //System.out.println(connection.isClosed());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

 }
