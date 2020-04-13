package com.github.orest.car_shop;
import com.github.orest.car_shop.service.CarService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws SQLException {

        SpringApplication.run(Main.class, args);
        CarService.getAllCars();
    }
}
