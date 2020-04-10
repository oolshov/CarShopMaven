package com.github.orest.car_shop;
import com.github.orest.car_shop.model.Car;
import com.github.orest.car_shop.service.CarShopService;
import com.github.orest.car_shop.storage.CarStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        List<Car> allCars = CarStorage.getAvailableCars();
        String text;

        Scanner input = new Scanner(System.in);

        System.out.println(CarShopService.getMessage("main"));
        text = input.nextLine();
        if (text.equals("all")){
            CarShopService.printAllCars(allCars);
            System.out.println(CarShopService.getMessage("main"));
            text = input.nextLine();
        }

        while (!text.equals("q")) {
            if (text.equals("all")){
                CarShopService.printAllCars(allCars);
                System.out.println(CarShopService.getMessage("main"));
                text = input.nextLine();
            } else if (text.equals("a")) {
                CarShopService.startAuction(allCars, input);
                System.out.println(CarShopService.getMessage("main"));
                text = input.nextLine();
            } else {
                CarShopService.getBrand(text, allCars);
                System.out.println(CarShopService.getMessage("main"));
                text = input.nextLine();
            }
        }
    }
}
