package com.example.labmpp.controller;

import com.example.labmpp.entities.Service;
import com.example.labmpp.entities.ServiceHistory;
import com.example.labmpp.services.ServiceHistoryService;
import com.example.labmpp.services.ServiceService;
import com.example.labmpp.utils.dtos.ServiceRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-history")
public class ServiceHistoryController {
    private final ServiceHistoryService serviceHistoryService;

    public ServiceHistoryController(ServiceHistoryService serviceHistoryService) {
        this.serviceHistoryService = serviceHistoryService;
//        this.serviceService = serviceService;
    }


    @GetMapping("")
    public ResponseEntity<List<ServiceHistory>> getAllServiceHistorys() {
        List<ServiceHistory> serviceHistorys = serviceHistoryService.findAll();
        return new ResponseEntity<>(serviceHistorys, HttpStatus.OK);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<ServiceHistory> getServiceHistoryById(@PathVariable("carId") Long id) {
        ServiceHistory serviceHistory = serviceHistoryService.findServiceHistoryByCarId(id);
        return new ResponseEntity<>(serviceHistory, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ServiceHistory> addServiceHistory(@RequestBody ServiceHistory serviceHistory) {
        ServiceHistory newServiceHistory = serviceHistoryService.addServiceHistory(serviceHistory);
        return new ResponseEntity<>(newServiceHistory, HttpStatus.CREATED);
    }

//    @PostMapping("/add-service-made")
//    public ResponseEntity<Service> addServiceMade(@RequestBody ServiceRequest service) throws Exception {
//        Service newServiceHistory = serviceHistoryService.addServiceMade(service);
//        return new ResponseEntity<>(newServiceHistory, HttpStatus.CREATED);
//    }

    @PutMapping("")
    public ResponseEntity<ServiceHistory> updateServiceHistory(@RequestBody ServiceHistory serviceHistory) {
        ServiceHistory updatedServiceHistory = serviceHistoryService.updateServiceHistory(serviceHistory);
        return new ResponseEntity<>(updatedServiceHistory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServiceHistory(@PathVariable("id") Long id) {
        serviceHistoryService.deleteServiceHistory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
