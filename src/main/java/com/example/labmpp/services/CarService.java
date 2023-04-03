package com.example.labmpp.services;

import com.example.labmpp.entities.Car;
import com.example.labmpp.entities.RentalTransaction;
import com.example.labmpp.exception.CarNotFoundException;
import com.example.labmpp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car by id " + id + " was not found"));
    }

    public List<Car> findAllWithHorsepowerHigherThan(Integer horsepower) {
        return this.findAll().stream()
                .filter(car -> car.getHorsepower() > horsepower)
                .collect(Collectors.toList());
    }

    public Float getAverageRentalsByMake(String make) {
        List<Car> cars = this.findAll().stream().filter(car -> car.getMake().contains(make)).collect(Collectors.toList());
        Set<Long> userIds = new HashSet<Long>();

        for (Car car : cars) {
            List<RentalTransaction> carRentalTransactions = car.getRentalTransactions();
            carRentalTransactions.forEach(carRentalTransaction -> userIds.add(carRentalTransaction.getCustomer().getId()));
        }

        if (!userIds.isEmpty() && !cars.isEmpty()) {
            return (float) userIds.size() / cars.size();
        }

        return 0f;
    }
}
