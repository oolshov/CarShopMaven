package com.github.orest.car_shop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
        return  "id:" + id +
                " brand: " + brand +
                ", model: " + model +
                ", color: " + color +
                ", damage: " + damage +
                ", price: " + price +
                ", quantity: " + quantity;
    }
}
