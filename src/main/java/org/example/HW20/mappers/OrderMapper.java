package org.example.HW20.mappers;

import org.example.HW20.dto.order.*;
import org.example.HW20.dto.payment.CreatePaymentDto;
import org.example.HW20.entity.Orders;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    GetOrderDto orderToDto(Orders orders);
    Orders orderDtoToOrder(CreateOrderDto createOrderDto);
    List<GetOrderForExpertDto> orderListToDtoList(List<Orders> ordersList);
    GetOrderForExpertDto orderListToDtoList(Orders orders);
    GetOrderWithOfferDto orderToOfferDto(Orders orders);
    List<GetOrderWithOfferDto> orderListToOrderListDto(List<Orders> ordersList);
    Orders orderEmailDtoToOrder(GetOrderByEmailDto getOrderByEmailDto);
    Orders orderCustomerEmailDtoToOrder(GetOrderByCustomerEmailDto getOrderByCustomerEmailDto);
    Orders paymentDtoToOrder(CreatePaymentDto createPaymentDto);
}
