package com.example.labmpp.services;

import com.example.labmpp.entities.Car;
import com.example.labmpp.entities.Customer;
import com.example.labmpp.entities.RentalTransaction;
import com.example.labmpp.exception.CarNotFoundException;
import com.example.labmpp.repository.CarRepository;
import com.example.labmpp.repository.CustomerRepository;
import com.example.labmpp.repository.RentalTransactionRepository;
import com.example.labmpp.utils.dtos.RentalTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalTransactionService {
    private final RentalTransactionRepository rentalTransactionRepository;
    private final CarService carService;
    private final CustomerService customerService;


    @Autowired
    public RentalTransactionService(RentalTransactionRepository rentalTransactionRepository, CarRepository carRepository, CarService carService, CustomerService customerService) {
        this.rentalTransactionRepository = rentalTransactionRepository;
        this.carService = carService;
        this.customerService = customerService;
    }

    public RentalTransaction addRentalTransaction(RentalTransactionRequest rentalTransactionRequest) throws Exception {

        try {
            RentalTransaction rentalTransaction = rentalTransactionRequest.getRentalTransaction();
            rentalTransaction.setCar(carService.findCarById(rentalTransactionRequest.getCarId()));
            rentalTransaction.setCustomer(customerService.findCustomerById(rentalTransactionRequest.getCustomerId()));

            rentalTransaction = rentalTransactionRepository.save(rentalTransaction);

            return rentalTransaction;
        } catch (Exception e) {
            throw new Exception("Rental transaction not saved");
        }
    }

    public RentalTransaction addRentalTransaction(RentalTransaction rentalTransaction) {
        return rentalTransactionRepository.save(rentalTransaction);
    }

    public List<RentalTransaction> findAll() {
        return rentalTransactionRepository.findAll();
    }

    public RentalTransaction updateRentalTransaction(RentalTransaction rentalTransaction) {
        return rentalTransactionRepository.save(rentalTransaction);
    }

    public void deleteRentalTransaction(Long id) {
        rentalTransactionRepository.deleteById(id);
    }

    public RentalTransaction findRentalTransactionById(Long id) {
        return rentalTransactionRepository.findRentalTransactionById(id)
                .orElseThrow(() -> new CarNotFoundException("RentalTransaction by id " + id + " was not found"));
    }
}