package com.github.orest.car_shop.service;

import com.github.orest.car_shop.model.Car;
import com.github.orest.car_shop.exceptions.*;
import com.github.orest.car_shop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public enum winner {
        USER,
        SYSTEM;
    }

    //@SneakyThrows need to discuss this (interesting lombok annotation)
    public void validateCars(List<Car> allCars) {
        System.out.println("Before if");
        if (allCars == null || allCars.size() < 1) {
            try {
                System.out.println("in try");
                throw new ZeroCarStorageException(getMessage("zero allCars"));
            } catch (ZeroCarStorageException e) {
                e.printStackTrace();
            }
        }
    }

    // this method search for available brand and return Car array
    public List<Car> getBrand(String brand, List<Car> allCars) {
        System.out.println("Before validate");
        validateCars(allCars);

        List<Car> foundCars = allCars.stream().filter(car -> car.getBrand().equals(brand)).collect(Collectors.toList());

        if (foundCars.size() > 0) {
            System.out.println("At this moment we have - " + foundCars.size() + " records with brand - " + brand);
            System.out.println("Available models is: ");

            // Try to use .foreach like foundCars.forEach(i -> System.out.println(i));
            foundCars.forEach(car -> System.out.println(car.getModel() + " - " + car.getQuantity() + " car(s), color - " + car.getColor() + ", price begins from - " + car.getPrice()));

        } else {
            System.out.println("Sorry, no available brand - " + brand + " at this moment or you entered wrong text, please try again.");
        }
        return foundCars;
    }

    // this method search for available model of car and return Car array
    public List<Car> getModel(String model, List<Car> allCars) {

        validateCars(allCars);

        List<Car> foundModel= allCars.stream().filter(car -> car.getModel().equals(model)).collect(Collectors.toList());

        if (foundModel.size() > 0) {
            System.out.println("At this moment we have - " + foundModel.get(0).getQuantity() + " cars of " + model + " with price - " + foundModel.get(0).getPrice());
        } else {
            System.out.println("Sorry, no available model - " + model + " at this moment or you entered wrong text, please try again.");
        }
        return foundModel;
    }

    // this method return all available cars from DB
    public Iterable<Car> getAllCars() {
        System.out.println(carRepository.findAll());
        return carRepository.findAll();
    }

    // method with stored messages
    public String getMessage(String message_type){
        String message;
        switch (message_type) {
            case "main": message = "\n\nPlease enter car brand you want to search for.  ('q' to Quit, 'all' to see all cars list, 'a' to start auction): "; break;
            case "model": message = "\n\nPlease enter car Model you want to participate in auction ('q' to Quit auction) :"; break;
            case "price": message = "\n\nPlease enter price for the car in +- 10% range of price ('q' to Quit auction) :"; break;
            case "zero allCars": message = "Something went wrong, list of cars is: null or 0 length"; break;
            default: message = "This type of message not stored into a system";
        }
        return message;
    }

    // this method calls an auction
    public void startAuction(List<Car> allCars, Scanner input) throws SQLException {

        validateCars(allCars);
        int price;
        String text;
        getAllCars();
        System.out.println(getMessage("model"));
        text = input.nextLine();

        while (!text.equals("q")) {
            String winner = "";
            List<Car> foundModel = new ArrayList<>();
            foundModel = getModel(text, allCars);

            if (foundModel.size() > 0) {
                System.out.println(getMessage("price"));
                if (!input.hasNextInt()) {
                    System.out.println("Please enter a number, you entered - '" + input.next() + "'");
                } else {
                    price = input.nextInt();
                    winner = getRandomPriceForAuction(price, foundModel).toString();
                    if (winner.equals("user")) {
                        System.out.println("You won - " + foundModel.get(0).getBrand() + " " + foundModel.get(0).getModel() + " for the: " + price + " USD");
                    } else {
                        System.out.println("Sorry You didn't won");
                    }
                }
                System.out.println(getMessage("model"));
                input.next(); // Remove '\n' from the buffer
                text = input.nextLine();
            } else {
                System.out.println(getMessage("model"));
                text = input.nextLine();
            }
        }
    }

    // this method generate random price and compare with user input and return winner name
    public winner getRandomPriceForAuction(int price, List<Car> foundModel) {
        List<Integer> randomPrice = new ArrayList<>();
        int max = foundModel.get(0).getPrice() + Math.round(foundModel.get(0).getPrice() / 10);
        int min = foundModel.get(0).getPrice() - Math.round(foundModel.get(0).getPrice() / 10);

        for (int num : new int[]{1,2}) {
            randomPrice.add(new Random().nextInt(max) + min);
        }
        if (randomPrice.get(0) < price && randomPrice.get(1) < price)  {
            return winner.SYSTEM;
        } else {
            return winner.USER;
        }
    }
}
