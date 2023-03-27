package com.example.labmpp.services;

import com.example.labmpp.entities.Car;
import com.example.labmpp.entities.ServiceHistory;
import com.example.labmpp.exception.CarNotFoundException;
import com.example.labmpp.repository.CarRepository;
import com.example.labmpp.repository.ServiceHistoryRepository;
import com.example.labmpp.utils.dtos.ServiceHistoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceHistoryService {
    private final ServiceHistoryRepository serviceHistoryRepository;
    private final CarRepository carRepository;

    @Autowired
    public ServiceHistoryService(ServiceHistoryRepository serviceHistoryRepository, CarRepository carRepository) {
        this.serviceHistoryRepository = serviceHistoryRepository;
        this.carRepository = carRepository;
    }

    public ServiceHistory addServiceHistory(ServiceHistoryRequest serviceHistoryRequest) throws Exception {

        if (carRepository.findCarById(serviceHistoryRequest.getCarId()).isPresent()) {
            Car car = carRepository.findCarById(serviceHistoryRequest.getCarId()).get();
            if (car.getServiceHistory() != null) {
                deleteServiceHistory(car.getServiceHistory().getId());
            }
            car.setServiceHistory(serviceHistoryRequest.getServiceHistory());

            return carRepository.save(car).getServiceHistory();
        }

        throw new Exception("Car id not found");
    }


    public List<ServiceHistory> findAll() {
        return serviceHistoryRepository.findAll();
    }

    public ServiceHistory updateServiceHistory(ServiceHistory serviceHistory) {
        return serviceHistoryRepository.save(serviceHistory);
    }

    public void deleteServiceHistory(Long id) {
        serviceHistoryRepository.deleteById(id);
    }

    public ServiceHistory findServiceHistoryById(Long id) {
        return serviceHistoryRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Service by id " + id + " was not found"));
    }
}
