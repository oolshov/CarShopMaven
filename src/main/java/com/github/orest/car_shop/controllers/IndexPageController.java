package com.github.orest.car_shop.controllers;

import com.github.orest.car_shop.repository.CarRepository;
import com.github.orest.car_shop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("cars", carRepository.findAll());
        return "index";
    }

}
