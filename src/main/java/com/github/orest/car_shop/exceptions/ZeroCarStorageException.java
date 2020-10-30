package com.github.orest.car_shop.exceptions;

public class ZeroCarStorageException extends NullPointerException {
    public ZeroCarStorageException(String errorMessage) {
        super(errorMessage);
    }
}
