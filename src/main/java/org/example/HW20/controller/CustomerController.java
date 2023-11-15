package org.example.HW20.controller;

import org.example.HW20.dto.customer.ChangeCustomerPasswordDto;
import org.example.HW20.dto.customer.CreateCustomerDto;
import org.example.HW20.dto.customer.GetCustomerDto;
import org.example.HW20.dto.customer.GetModifiedCustomerPasswordTimeDto;
import org.example.HW20.entity.Customer;
import org.example.HW20.mappers.CustomerMapper;
import org.example.HW20.mappers.CustomerMapperImpl;
import org.example.HW20.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerServiceImpl customerService;
    private final CustomerMapper customerMapper;

    @PostMapping("/create")
    public GetCustomerDto create(@Valid @RequestBody CreateCustomerDto customerDto) {
        Customer customer = customerMapper.dtoToCustomer(customerDto);
        return customerMapper.customerToDto(customerService.create(customer));
    }

    @GetMapping("/findById")
    public GetCustomerDto findById(@RequestParam Long id) {
        return customerMapper.customerToDto(customerService.findById(id));
    }

    @GetMapping("/findAll")
    public List<GetCustomerDto> findAll() {
        return customerMapper.customerListToDtoList(customerService.findAll());
    }

    @PutMapping("/changePassword")
    public GetModifiedCustomerPasswordTimeDto changePassword(@Valid @RequestBody ChangeCustomerPasswordDto changeCustomerPasswordDto) {
        Customer customer = customerMapper.changePasswordDtoToCustomer(changeCustomerPasswordDto);
        return customerService.changePassword(customer);
    }

    @GetMapping("/search")
    public List<GetCustomerDto> findAll(@RequestParam(value = "search", required = false) String search) {
        return customerMapper.customerListToDtoList(customerService.search(search));
    }
}
