package com.example.labmpp.controller;

import com.example.labmpp.entities.Car;
import com.example.labmpp.services.CarService;
import com.example.labmpp.utils.dtos.CustomView.CustomJsonView;
import com.example.labmpp.utils.dtos.SortedCarsDto;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonView.FullDataCar.class)
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id) {
        Car car = carService.findCarById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping("/{horsepower}")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<List<Car>> getCarsWithHorsepowerHigherThan(@PathVariable("horsepower") Integer horsepower) {
        List<Car> cars = carService.findAllWithHorsepowerHigherThan(horsepower);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping("")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<Car> addCar(@RequestBody Car car) throws Exception {
        Car newCar;
        try {
            newCar = carService.addCar(car);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PutMapping("")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        Car updatedCar = carService.updateCar(car);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //The average number of UNIQUE customers that rented a certain make, cars sorted in ascending order
    @GetMapping("/most-rented/{make}")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<SortedCarsDto> getMostRentedByMake(@PathVariable("make") String make) {
        SortedCarsDto sortedCarsDto = new SortedCarsDto();

        sortedCarsDto.setCars(carService.getCarsAboveAverageSortedByMake(make));
        sortedCarsDto.setAverage(carService.getAverageRentalsByMake(make));

        return new ResponseEntity<>(sortedCarsDto, HttpStatus.OK);
    }

    //cars sored by number of Rentals in ascending order (not unique customers) above a certain HP
    @GetMapping("/sorted-rentals-above-hp/{hp}")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<SortedCarsDto> getMostRentedByMake(@PathVariable("hp") Integer hp) {
        SortedCarsDto sortedCarsDto = new SortedCarsDto();

        sortedCarsDto.setCars(carService.getCarsAboveHpSortedByRentals(hp));
        sortedCarsDto.setAverage(carService.getAverageRentalsAboveHp(hp));

        return new ResponseEntity<>(sortedCarsDto, HttpStatus.OK);
    }
}
