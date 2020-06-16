package com.github.orest.car_shop.controllers;

import com.github.orest.car_shop.exceptions.CarNotFoundException;
import com.github.orest.car_shop.model.Car;
import com.github.orest.car_shop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cars")
public class CarApiController {

    @Autowired
    private CarRepository carRepository;


    @GetMapping
    Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    @PostMapping
    Car createCar(@Valid @RequestBody Car newCar) {
        return carRepository.save(newCar);
    }

    @GetMapping("/{id}")
    Car getCar(@PathVariable int id) {
        return carRepository.findById(id);
    }

    //@ExceptionHandler(NullPointerException.class)
    @PutMapping("/{id}")
    Car updateCar(@Valid @RequestBody Car newCar, @PathVariable int id) {
        try {
            carRepository.findById(id).updateRecord(newCar);
            return carRepository.save(carRepository.findById(id));
        } catch (NullPointerException e) {
            throw new CarNotFoundException(id);
        }

    }

    @DeleteMapping("/{id}")
    void deleteCar(@PathVariable int id) {
        try {
            carRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CarNotFoundException(id);
        }
    }
}
