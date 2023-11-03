package org.example.HW20.service;

import org.example.HW20.dto.customer.GetModifiedCustomerPasswordTimeDto;
import org.example.HW20.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer create(Customer customer);
    Customer findById(Long id);
    List<Customer> findAll();
    Customer findByEmail(String email);
    GetModifiedCustomerPasswordTimeDto changePassword(Customer customer);

    List<Customer> search(String request);
}
