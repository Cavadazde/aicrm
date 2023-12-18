package com.example.aicrm.service;

import com.example.aicrm.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long customerId);

    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);

    void deleteCustomer(Long customerId);
}
