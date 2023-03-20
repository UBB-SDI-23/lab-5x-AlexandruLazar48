package com.example.labmpp.controller;

import com.example.labmpp.entities.Service;
import com.example.labmpp.services.ServiceHistoryService;
import com.example.labmpp.utils.dtos.ServiceRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private final ServiceHistoryService serviceHistoryService;

    public ServiceController(ServiceHistoryService serviceHistoryService) {
        this.serviceHistoryService = serviceHistoryService;
    }

    @PostMapping("")
    public ResponseEntity<Service> addServiceMade(@RequestBody ServiceRequest service) throws Exception {
        Service newServiceHistory = serviceHistoryService.addServiceMade(service);
        return new ResponseEntity<>(newServiceHistory, HttpStatus.CREATED);
    }
}
