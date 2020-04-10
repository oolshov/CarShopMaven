package com.github.orest.car_shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class HomePageController {
    @GetMapping("/home")
    public String home(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        model.addAttribute("name", name);
        model.addAttribute("time", formatter.format(Calendar.getInstance().getTime()));
        return "home";
    }

}
