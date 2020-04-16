package com.github.orest.car_shop.controllers;

import com.github.orest.car_shop.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@Controller
public class IndexPageController {
    @GetMapping
    public String index(Model model) throws SQLException {

        model.addAttribute("cars", CarService.getAllCars());
        return "index";
    }

}
