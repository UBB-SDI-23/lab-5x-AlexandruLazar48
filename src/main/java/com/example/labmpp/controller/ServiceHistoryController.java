package com.example.labmpp.controller;

import com.example.labmpp.entities.ServiceHistory;
import com.example.labmpp.services.ServiceHistoryService;
import com.example.labmpp.utils.dtos.CustomView.CustomJsonView;
import com.example.labmpp.utils.dtos.ServiceHistoryRequest;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-history")
public class ServiceHistoryController {

    private ServiceHistoryService serviceHistoryService;

    public ServiceHistoryController(ServiceHistoryService serviceHistoryService) {
        this.serviceHistoryService = serviceHistoryService;
    }

    @GetMapping("")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<List<ServiceHistory>> getAllServiceHistorys() {
        List<ServiceHistory> serviceHistorys = serviceHistoryService.findAll();
        return new ResponseEntity<>(serviceHistorys, HttpStatus.OK);
    }

    @GetMapping("/{carId}")
    @JsonView(CustomJsonView.FullDataServiceHistory.class)
    public ResponseEntity<ServiceHistory> getServiceHistoryById(@PathVariable("carId") Long id) {
        ServiceHistory serviceHistory = serviceHistoryService.findServiceHistoryById(id);
        return new ResponseEntity<>(serviceHistory, HttpStatus.OK);
    }

    @PostMapping("")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<ServiceHistory> addServiceHistory(@RequestBody ServiceHistoryRequest serviceHistory) throws Exception {
        ServiceHistory newServiceHistory = serviceHistoryService.addServiceHistory(serviceHistory);
        return new ResponseEntity<>(newServiceHistory, HttpStatus.CREATED);
    }

    @PutMapping("")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<ServiceHistory> updateServiceHistory(@RequestBody ServiceHistory serviceHistory) {
        ServiceHistory updatedServiceHistory = serviceHistoryService.updateServiceHistory(serviceHistory);
        return new ResponseEntity<>(updatedServiceHistory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @JsonView(CustomJsonView.FullDataServiceHistory.class)
    public ResponseEntity<?> deleteServiceHistory(@PathVariable("id") Long id) {
        serviceHistoryService.deleteServiceHistory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
