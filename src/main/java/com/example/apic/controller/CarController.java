package com.example.apic.controller;

import com.example.apic.model.Car;
import com.example.apic.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("newCar", new Car());
        return "cars";
    }

    @GetMapping("/cars/{id}")
    public String getCarById(@PathVariable long id, Model model) {
        Optional<Car> carById = carService.getCarById(id);
        if (carById.isPresent()) {
            model.addAttribute("car", carById.get());
            return "car";
        }
        return "redirect:/error";
    }

    @PostMapping("/add-car")
    public String addCar(Model model, @ModelAttribute Car car) {
        boolean add = carService.addCar(car);
        if (add) {
            model.addAttribute("newCar", new Car());
            return "redirect:/cars";
        }
        return "redirect:/error";
    }

    @GetMapping("/delete-car/{id}")
    public String removeCar(@PathVariable long id) {
        if (carService.removeCar(id)) {
            return "redirect:/cars";
        }
        return "redirect:/error";
    }

    @GetMapping("/edit-car/{id}")
    public String postModifyCar(Model model, @PathVariable long id) {
        Optional<Car> carById = carService.getCarById(id);
        Car myCar = carById.orElseGet(Car::new);
        model.addAttribute("newCar", new Car());
        model.addAttribute("car", myCar);
        return "edit-car";
    }

    @PostMapping("/edit-car")
    public String modifyCar(@ModelAttribute Car car) {
        if (carService.modCar(car)) {
            return "redirect:/cars";
        }
        return "redirect:/error";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

}

