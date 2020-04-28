package com.github.orest.car_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

@SpringBootApplication
//@RestController
public class Main {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class, args);

        //AbstractApplicationContext  context = new AnnotationConfigApplicationContext(AppConfig.class);
        //DBWorkerService service = (DBWorkerService) context.getBean("DBWorkerService");

        //service.connectDB();

        //DBWorkerService service = new DBWorkerService();
        //System.out.println(service.getUrl());
        System.out.println("Hello");

    }
}
