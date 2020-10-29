package com.github.orest.car_shop.controllers;

import com.github.orest.car_shop.model.Car;
import com.github.orest.car_shop.repository.CarRepository;
import com.github.orest.car_shop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping
@Controller
public class IndexPageController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public String index(Model model) {

        if (model.containsAttribute("car_updated")) {
            model.addAttribute("car_updated", model.getAttribute(("car_updated")));
        }
        model.addAttribute("fields", Car.class.getDeclaredFields());
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("available_text", "Here is a list of available cars:");
        return "index";
    }

    @PostMapping("find")
    public String find(@RequestParam String find, Model model) {
        model.addAttribute("fields", Car.class.getDeclaredFields());
        if (find != null && !find.isEmpty() ) {
            model.addAttribute("cars", carRepository.findByBrand(find));
            model.addAttribute("available_text", "Here is a list of available cars for brand:" + find);
        } else {
            model.addAttribute("cars", carRepository.findAll());
            model.addAttribute("available_text", "Here is a list of available cars:");
        }

        return "index";
    }

}
