package com.example.labmpp.services;

import com.example.labmpp.entities.Car;
import com.example.labmpp.exception.CarNotFoundException;
import com.example.labmpp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car) {
        car.setCode(UUID.randomUUID());
        return carRepository.save(car);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car findCarById(Long id) {
        return carRepository.findCarById(id)
                .orElseThrow(() -> new CarNotFoundException("Car by id " + id + " was not found"));
    }
}
