package org.example.HW20.service;

import org.example.HW20.dto.customer.GetModifiedCustomerPasswordTimeDto;
import org.example.HW20.entity.Customer;
import org.example.HW20.exceptions.user.PasswordNotMatchException;
import org.example.HW20.exceptions.user.UserExistException;
import org.example.HW20.exceptions.user.UserNotFoundException;
import org.example.HW20.repository.CustomerRepository;
import org.example.HW20.repository.CustomerSearch;
import org.example.HW20.utils.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerSearch customerSearch;

    @Override
    public Customer create(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new UserExistException("customer with given email :-- " + customer.getEmail() + " -- has already registered.");
        }
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("no customer found with this ID.")
        );
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("no customer found with this email.")
        );
    }

    @Override
    public GetModifiedCustomerPasswordTimeDto changePassword(Customer customer) {
        Customer newCustomer = findByEmail(customer.getEmail());
        if (!customer.getPassword().equals(customer.getRepeatPassword())) {
            throw new PasswordNotMatchException("the entered password does not match with its repetition.");
        }
        newCustomer.setPassword(customer.getPassword());
        customerRepository.save(newCustomer);
        return new GetModifiedCustomerPasswordTimeDto(LocalDateTime.now(), "the password has been successfully updated.");
    }

    @Override
    public List<Customer> search(String request) {
        List<SearchCriteria> params = new ArrayList<>();
        if (request != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|!|>=|<=)(\\w+?),");
            Matcher matcher = pattern.matcher(request + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        return customerSearch.searchUser(params);
    }
}
