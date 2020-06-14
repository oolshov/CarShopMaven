package com.github.orest.car_shop.controllers;

import com.github.orest.car_shop.model.Car;
import com.github.orest.car_shop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequestMapping
@Controller
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/edit_car")
    public String edit(@RequestParam int id, RedirectAttributes attributes, Model model) {

        model.addAttribute("fields", Car.class.getDeclaredFields());
        if (model.containsAttribute("car")) {
            model.addAttribute("cars", model.getAttribute("car"));
        } else {
            model.addAttribute("cars", carRepository.findById(id));
        }
        model.addAttribute("available_text", "Car edit page:");


        return "edit_car";
    }

    @PostMapping("update_car")
    public String redirectIndexUpdate(@RequestParam int id, RedirectAttributes attributes, @Valid Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            );
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream().collect(collector);
            errorsMap.forEach((key, value) -> {
                if (value.contains("NumberFormatException")){
                    value = "Should be a number";
                }
                attributes.addFlashAttribute(key, value);
            });
            attributes.addFlashAttribute(car);
            return ("redirect:/edit_car?id="+id);
        } else {
            carRepository.findById(id).updateRecord(car);
            carRepository.save(carRepository.findById(id));
            attributes.addFlashAttribute("car_updated", "Car is successfully updated");
        }
        return ("redirect:/");
    }

    @GetMapping("delete_car")
    public RedirectView redirectIndexDelete(@RequestParam int id, RedirectAttributes attributes) {

        try {
            carRepository.deleteById(id);
            attributes.addFlashAttribute("car_updated", "Car is successfully deleted");
        } catch (Exception e) {
            attributes.addFlashAttribute("car_updated", e);
        }

        return new RedirectView("/");
    }

    @GetMapping("/new_car")
    public String new_car(RedirectAttributes attributes, Model model) {

        model.addAttribute("fields", Car.class.getDeclaredFields());
        if (model.containsAttribute("car")) {
            model.addAttribute("car", model.getAttribute("car"));
        }
        model.addAttribute("available_text", "New car page:");
        return "new_car";
    }

    @PostMapping("add_new_car")
    public String redirectIndexAdd(RedirectAttributes attributes, @Valid Car car, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            );
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream().collect(collector);
            errorsMap.forEach((key, value) -> {
                if (value.contains("NumberFormatException")){
                    value = "Should be a number";
                }
                attributes.addFlashAttribute(key, value);
            });
            attributes.addFlashAttribute(car);
            return ("redirect:/new_car");
        } else {
            carRepository.save(car);
            attributes.addFlashAttribute("car_updated", "Car is successfully created");
        }
        return ("redirect:/");
    }


}
