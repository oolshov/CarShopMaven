package com.github.orest.car_shop.repository;

import com.github.orest.car_shop.model.Car;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CarRepository extends CrudRepository<Car, Integer> {

}
