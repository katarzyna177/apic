package com.example.apic.service;

import com.example.apic.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> getAllCars();

    Optional<Car> getCarById(long id);

    List<Car> getCarsByColor(String color);

    boolean addCar(Car car);

    Optional<Car> modCar(long id, Car car);

    Optional<Car> modifyCarPut(long id, Car car);

    boolean removeCar(long id);

    boolean modCar(Car requestCar);
}
