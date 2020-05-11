package com.github.orest.car_shop;

import com.github.orest.car_shop.model.Car;
import com.github.orest.car_shop.service.CarService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class, args);
    }
}
