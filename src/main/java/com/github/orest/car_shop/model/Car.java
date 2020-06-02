package com.github.orest.car_shop.model;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Brand is mandatory")
    private String brand;

    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotBlank(message = "Color is mandatory")
    private String color;

    private String damage;

    @NotNull(message = "Price is mandatory")
    //@Digits(integer = 100000000000000, fraction = 5, message = "Price should be a number")
    //@Pattern(regexp="\\d", message = "Price is mandatory")
    private int price;

    @NotNull(message = "Quantity is mandatory")
    private int quantity;

    public Car(String brand, String model, String color, String damage, int price, int quantity) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.damage = damage;
        this.price = price;
        this.quantity = quantity;
    }

    public void update_record(String brand, String model, String color, String damage, int price, int quantity) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.damage = damage;
        this.price = price;
        this.quantity = quantity;
    }

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
