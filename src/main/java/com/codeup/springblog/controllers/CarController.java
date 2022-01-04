package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {
    private final CarRepository carDoa;

    public CarController(CarRepository carDoa){
        this.carDoa = carDoa;
    }

    @GetMapping("/cars")
    public String carIndex(Model model){
        model.addAttribute("cars", carDoa.findAll());

        model.addAttribute("chevys", carDoa.findAllByMake("Cheverolet"));

        return "cars";
    }
}
