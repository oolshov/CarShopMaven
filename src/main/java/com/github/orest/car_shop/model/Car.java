package com.github.orest.car_shop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // This tells Hibernate to make a table out of this class
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
