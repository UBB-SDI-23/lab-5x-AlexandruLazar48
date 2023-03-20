package com.example.labmpp.services;

import com.example.labmpp.entities.ServiceHistory;
import com.example.labmpp.exception.CarNotFoundException;
import com.example.labmpp.repository.CarRepository;
import com.example.labmpp.repository.ServiceHistoryRepository;
import com.example.labmpp.repository.ServiceRepository;
import com.example.labmpp.utils.dtos.ServiceHistoryDTO;
import com.example.labmpp.utils.dtos.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServiceHistoryService {
    private final ServiceHistoryRepository serviceHistoryRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceHistoryService(ServiceHistoryRepository serviceHistoryRepository, ServiceRepository serviceRepository, CarRepository carRepository) {
        this.serviceHistoryRepository = serviceHistoryRepository;
        this.serviceRepository = serviceRepository;
    }

    public ServiceHistory addServiceHistory(ServiceHistory serviceHistory) {
        return serviceHistoryRepository.save(serviceHistory);
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

    public List<ServiceHistory> findAll() {
        return serviceHistoryRepository.findAll();
    }

    public ServiceHistory updateServiceHistory(ServiceHistory serviceHistory) {
        return serviceHistoryRepository.save(serviceHistory);
    }

    public void deleteServiceHistory(Long id) {
        serviceHistoryRepository.deleteById(id);
    }

    public ServiceHistory findServiceHistoryByCarId(Long id) {
        return serviceHistoryRepository.findServiceHistoryByCarId(id)
                .orElseThrow(() -> new CarNotFoundException("Service by id " + id + " was not found"));
    }


    public ServiceHistoryDTO convertToDto(ServiceHistory serviceHistory) {
        ServiceHistoryDTO serviceHistoryDTO = new ServiceHistoryDTO();

        serviceHistoryDTO.setId(serviceHistory.getId());
        serviceHistoryDTO.setAllowedToRun(serviceHistory.getIsAllowedToRun());
        serviceHistoryDTO.setHasStockBody(serviceHistory.getHasStockBody());
        serviceHistoryDTO.setYearlyTax(serviceHistory.getYearlyTax());
        serviceHistoryDTO.setInsuranceTax(serviceHistory.getInsuranceTax());
        serviceHistoryDTO.setCarId(serviceHistory.getCar().getId());
        serviceHistoryDTO.setHasStockPowertrain(serviceHistory.getHasStockPowertrain());

        return serviceHistoryDTO;
    }

//    public ServiceHistoryDTO convertDtoToEntity(ServiceHistoryDTO serviceHistoryDTO) {
//        se
//
//        serviceHistoryDTO.setId(serviceHistory.getId());
//        serviceHistoryDTO.setAllowedToRun(serviceHistory.getIsAllowedToRun());
//        serviceHistoryDTO.setHasStockBody(serviceHistory.getHasStockBody());
//        serviceHistoryDTO.setYearlyTax(serviceHistory.getYearlyTax());
//        serviceHistoryDTO.setInsuranceTax(serviceHistory.getInsuranceTax());
//        serviceHistoryDTO.setCarId(serviceHistory.getCar().getId());
//        serviceHistoryDTO.setHasStockPowertrain(serviceHistory.getHasStockPowertrain());
//
//        return serviceHistoryDTO;
//    }
}
