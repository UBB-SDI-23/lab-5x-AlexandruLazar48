package com.example.labmpp.services;

import com.example.labmpp.entities.Service;
import com.example.labmpp.entities.ServiceHistory;
import com.example.labmpp.exception.CarNotFoundException;
import com.example.labmpp.repository.ServiceHistoryRepository;
import com.example.labmpp.repository.ServiceRepository;
import com.example.labmpp.utils.dtos.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceHistoryRepository serviceHistoryRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository, ServiceHistoryRepository serviceHistoryRepository) {
        this.serviceRepository = serviceRepository;
        this.serviceHistoryRepository = serviceHistoryRepository;
    }

    public Service addService(Service service) {
        return serviceRepository.save(service);
    }

    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    public Service updateService(Service service) {
        return serviceRepository.save(service);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    public Service findServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Service by id " + id + " was not found"));
    }

    public com.example.labmpp.entities.Service addServiceMade(ServiceRequest serviceRequest) throws Exception {
        Optional<ServiceHistory> serviceHistory = serviceHistoryRepository.findById(serviceRequest.getServiceHistoryId());
        com.example.labmpp.entities.Service service = serviceRequest.getService();

        if (serviceHistory.isPresent()) {
            service.setServiceHistory(serviceHistory.get());
            return serviceRepository.save(service);
        }

        throw new Exception("Something went wrong, history not found");
    }
}
