package org.example.HW20.mappers;

import org.example.HW20.dto.customer.ChangeCustomerPasswordDto;
import org.example.HW20.dto.customer.CreateCustomerDto;
import org.example.HW20.dto.customer.GetCustomerDto;
import org.example.HW20.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    GetCustomerDto customerToDto(Customer customer);
    Customer dtoToCustomer(CreateCustomerDto customerDto);
    List<GetCustomerDto> customerListToDtoList(List<Customer> customerList);
    Customer changePasswordDtoToCustomer(ChangeCustomerPasswordDto changeCustomerPasswordDto);
}
