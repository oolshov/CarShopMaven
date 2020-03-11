package com.github.orest.car_shop.model;

import lombok.*;

@AllArgsConstructor
@Data
public class Car {
    private int id;
    private String brand;
    private String model;
    private String color;
    private String damage;
    private int price;
    private int quantity;
}
