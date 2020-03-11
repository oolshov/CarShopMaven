package com.github.orest.car_shop.service;

import com.github.orest.car_shop.model.Car;
import com.github.orest.car_shop.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class CarShopService {

    //@SneakyThrows need to discuss this (interesting lombok annotation)
    public static void validateCars(List<Car> allCars) {
        if (allCars == null || allCars.size() < 1) {
            try {
                throw new ZeroCarStorageException(getMessage("zero allCars"));
            } catch (ZeroCarStorageException e) {
                e.printStackTrace();
            }
        }
    }

    // this method search for available brand and return Car array
    public static List<Car> getBrand(String brand, List<Car> allCars) {

        validateCars(allCars);

        //Stream<Car> foundCars = Stream.empty();
        List<Car> foundCars = new ArrayList<>();
        allCars.forEach(car -> {
            if (car.getBrand().equals(brand)) {
                foundCars.add(car);
            }
        });

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
    public static List<Car> getModel(String model, List<Car> allCars) throws IllegalArgumentException {

        validateCars(allCars);

        List<Car> foundModel = new ArrayList<>();
        allCars.forEach(car -> {
            if (car.getBrand().equals(model)) {
                foundModel.add(car);
            }
        });

        if (foundModel.size() > 0) {
            System.out.println("At this moment we have - " + foundModel.get(0).getQuantity() + " cars of " + model + " with price - " + foundModel.get(0).getPrice());
        } else {
            System.out.println("Sorry, no available model - " + model + " at this moment or you entered wrong text, please try again.");
        }
        return foundModel;
    }

    // this method print all available cars
    public static void printAllCars(List<Car> allCars) {

        validateCars(allCars);

        allCars.forEach(car -> System.out.println(car.getBrand() + " " + car.getModel() + " - " + car.getQuantity() + " car(s), color - " + car.getColor() + ", price begins from - " + car.getPrice()));
    }

    // method with stored messages
    public static String getMessage(String message_type){
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
    public static void startAuction(List<Car> allCars, Scanner input) {

        validateCars(allCars);
        int price;
        String text;
        printAllCars(allCars);
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
                    winner = getRandomPriceForAuction(price, foundModel);
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
    public static String getRandomPriceForAuction(int price, List<Car> foundModel) {
        List<Integer> randomPrice = new ArrayList<>();
        int max = foundModel.get(0).getPrice() + Math.round(foundModel.get(0).getPrice() / 10);
        int min = foundModel.get(0).getPrice() - Math.round(foundModel.get(0).getPrice() / 10);
        String winner = "user";

        for (int num : new int[]{1,2}) {
            randomPrice.add(new Random().nextInt(max) + min);
        }
        if (randomPrice.get(0) < price && randomPrice.get(1) < price)  {
            winner = "system";
        }
        return winner;
    }
}
