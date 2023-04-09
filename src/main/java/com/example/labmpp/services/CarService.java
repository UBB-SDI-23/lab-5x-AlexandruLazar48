package com.example.labmpp.services;

import com.example.labmpp.entities.Car;
import com.example.labmpp.entities.RentalTransaction;
import com.example.labmpp.exception.CarNotFoundException;
import com.example.labmpp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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
        List<Car> cars = this.findAll().stream().filter(car -> car.getMake().toLowerCase().equalsIgnoreCase(make)).collect(Collectors.toList());
        int count = 0;

        for (Car car : cars) {
            Set<Long> userIds = new HashSet<Long>();
            List<RentalTransaction> carRentalTransactions = car.getRentalTransactions();
            if (carRentalTransactions != null) {
                carRentalTransactions.forEach(carRentalTransaction -> userIds.add(carRentalTransaction.getCustomer().getId()));
                count = count + userIds.size();
            }
        }

        if (count !=0 && !cars.isEmpty()) {
            return (float) count / cars.size();
        }

        return 0f;
    }

    public List<Car> getCarsAboveAverageSortedByMake(String make) {
        List<Car> cars = this.findAll().stream().filter(car -> car.getMake().toLowerCase().equalsIgnoreCase(make)).collect(Collectors.toList());
        List<Pair<Integer, Car>> orderedCars = new ArrayList<>();

        for (Car car : cars) {
            Set<Long> userIds = new HashSet<Long>();
            List<RentalTransaction> carRentalTransactions = car.getRentalTransactions();
            if (carRentalTransactions != null) {
                carRentalTransactions.forEach(carRentalTransaction -> userIds.add(carRentalTransaction.getCustomer().getId()));
            }
            orderedCars.add(Pair.of(userIds.size(), car));
        }

        orderedCars.sort(Comparator.comparing(Pair::getFirst));

        List<Car> orderedCarsList = new ArrayList<>();
        orderedCars.forEach(pair -> orderedCarsList.add(pair.getSecond()));

        return orderedCarsList;
    }

    public Float getAverageRentalsAboveHp(Integer hp) {
        List<Car> cars = this.findAll().stream().filter(car -> car.getHorsepower() > hp).collect(Collectors.toList());
        int count = 0;

        for (Car car : cars) {
            List<Long> userIds = new ArrayList<Long>();
            List<RentalTransaction> carRentalTransactions = car.getRentalTransactions();
            if (carRentalTransactions != null) {
                carRentalTransactions.forEach(carRentalTransaction -> userIds.add(carRentalTransaction.getCustomer().getId()));
                count = count + userIds.size();
            }
        }

        if (count !=0 && !cars.isEmpty()) {
            return (float) count / cars.size();
        }

        return 0f;
    }

    public List<Car> getCarsAboveHpSortedByRentals(Integer hp) {
        List<Car> cars = this.findAll().stream().filter(car -> car.getHorsepower() > hp).collect(Collectors.toList());
        List<Pair<Integer, Car>> orderedCars = new ArrayList<>();

        for (Car car : cars) {
            List<Long> userIds = new ArrayList<>();
            List<RentalTransaction> carRentalTransactions = car.getRentalTransactions();
            if (carRentalTransactions != null) {
                carRentalTransactions.forEach(carRentalTransaction -> userIds.add(carRentalTransaction.getCustomer().getId()));
            }
            orderedCars.add(Pair.of(userIds.size(), car));
        }

        orderedCars.sort(Comparator.comparing(Pair::getFirst));
        List<Car> orderedCarsList = new ArrayList<>();
        orderedCars.forEach(pair -> orderedCarsList.add(pair.getSecond()));

        return orderedCarsList;
    }
}
