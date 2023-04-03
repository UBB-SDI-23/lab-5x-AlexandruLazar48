package com.example.labmpp.services;

import com.example.labmpp.entities.Customer;
import com.example.labmpp.exception.CarNotFoundException;
import com.example.labmpp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findCustomerById(id)
                .orElseThrow(() -> new CarNotFoundException("Customer by id " + id + " was not found"));
    }
}
