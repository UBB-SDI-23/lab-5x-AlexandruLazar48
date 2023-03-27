package com.example.labmpp.controller;

import com.example.labmpp.entities.Service;
import com.example.labmpp.entities.Service;
import com.example.labmpp.services.ServiceHistoryService;
import com.example.labmpp.services.ServiceService;
import com.example.labmpp.utils.dtos.CustomView.CustomJsonView;
import com.example.labmpp.utils.dtos.ServiceRequest;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping("")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<Service> addServiceMade(@RequestBody ServiceRequest service) throws Exception {
        Service newServiceHistory = serviceService.addServiceMade(service);
        return new ResponseEntity<>(newServiceHistory, HttpStatus.CREATED);
    }

    @GetMapping("")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<List<Service>> getAllService() {
        List<Service> services = serviceService.findAll();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonView.FullDataService.class)
    public ResponseEntity<Service> getServiceById(@PathVariable("id") Long id) {
        Service service = serviceService.findServiceById(id);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @PutMapping("")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<Service> updateService(@RequestBody Service service) {
        Service updatedService = serviceService.updateService(service);
        return new ResponseEntity<>(updatedService, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable("id") Long id) {
        serviceService.deleteService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
