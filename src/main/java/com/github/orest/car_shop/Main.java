package com.github.orest.car_shop;
import com.github.orest.car_shop.service.CarService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {

    public static void main(String[] args) throws SQLException {

        SpringApplication.run(Main.class, args);
        //@Value("${db.url}")
        //String message;
    }
}
