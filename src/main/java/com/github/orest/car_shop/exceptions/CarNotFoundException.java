package com.github.orest.car_shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CarNotFoundException extends NullPointerException {
    public CarNotFoundException(int id) {
        super("Could not find Car with id - " + id);
    }
}
