package com.github.orest.car_shop.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.*;

@AllArgsConstructor
@Data
public class DBWorkerService {
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/car_shop";
    private static final String login = "root";
    private static final String password = "root";

    private Statement statement;

    public DBWorkerService() {
        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //    try {
    //        Driver driver = new com.mysql.cj.jdbc.Driver();
    //        DriverManager.registerDriver(driver);
    //    } catch (SQLException e) {
    //        e.printStackTrace();
    //    }

 }
