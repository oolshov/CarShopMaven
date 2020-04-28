package com.github.orest.car_shop.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {

    private int id;
    private String brand;
    private String model;
    private String color;
    private String damage;
    private int price;
    private int quantity;

    @Override
    public String toString() {
        return  " brand: " + brand +
                ", model: " + model +
                ", color: " + color +
                ", damage: " + damage +
                ", price: " + price +
                ", quantity: " + quantity;
    }
}
