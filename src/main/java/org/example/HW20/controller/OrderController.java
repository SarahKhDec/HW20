package org.example.HW20.controller;

import org.example.HW20.dto.order.*;
import org.example.HW20.entity.Orders;
import org.example.HW20.mappers.OrderMapperImpl;
import org.example.HW20.service.OrderServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderServiceImpl orderService;
    private final OrderMapperImpl orderMapper;

    @PostMapping("/create")
    public GetOrderDto create(@Valid @RequestBody CreateOrderDto createOrderDto) {
        Orders orders = orderMapper.orderDtoToOrder(createOrderDto);
        return orderMapper.orderToDto(orderService.create(orders));
    }

    @PostMapping("/findAllByExpertSubService")
    public List<GetOrderForExpertDto> findAllByExpertSubService(@Valid @RequestBody GetOrderByEmailDto getOrderByEmailDto) {
        Orders orders = orderMapper.orderEmailDtoToOrder(getOrderByEmailDto);
        return orderMapper.orderListToDtoList(orderService.findAllByExpertSuService(orders));
    }

    @PostMapping("/startOrder")
    public GetOrderWithOfferDto changeOrderStatusToStarted(@Valid @RequestBody GetOrderByCustomerEmailDto getOrderByCustomerEmailDto) {
        Orders orders = orderMapper.orderCustomerEmailDtoToOrder(getOrderByCustomerEmailDto);
        return orderMapper.orderToOfferDto(orderService.updateOrderStatusToStarted(orders));
    }

    @PostMapping("/doneOrder")
    public GetOrderWithOfferDto changeOrderStatusToDone(@Valid @RequestBody GetOrderByCustomerEmailDto getOrderByCustomerEmailDto) {
        Orders orders = orderMapper.orderCustomerEmailDtoToOrder(getOrderByCustomerEmailDto);
        return orderMapper.orderToOfferDto(orderService.updateOrderStatusToDone(orders));
    }

    @PostMapping("/findAllDoneOrder")
    public List<GetOrderWithOfferDto> findAllDoneOrder(@Valid @RequestBody GetOrderByEmailDto getOrderByEmailDto) {
        Orders orders = orderMapper.orderEmailDtoToOrder(getOrderByEmailDto);
        return orderMapper.orderListToOrderListDto(orderService.findAllDoneOrder(orders));
    }
}
