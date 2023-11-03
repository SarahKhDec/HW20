package org.example.HW20.service;

import org.example.HW20.entity.Offers;
import org.example.HW20.entity.Orders;
import org.example.HW20.entity.SubService;

import java.util.List;
import java.util.Set;

public interface OrderService {

    Orders create(Orders orders);
    List<Orders> findAllByExpertSuService(Orders orders);
    Orders findByIdAndStatus(Long id, Set<SubService> subServices);
    void updateOrderStatusToSelect(Orders orders, Offers offers);
    Orders findByIdAndCustomerId(Long id, Long customerId);
    Orders updateOrderStatusToComePlace(Orders orders, Offers offers);
    Orders updateOrderStatusToStarted(Orders orders);
    Orders updateOrderStatusToDone(Orders orders);
    List<Orders> findAllDoneOrder(Orders orders);
    Orders paymentWithOneMethod(Orders orders);
    Boolean paymentWithTwoMethod(Orders orders);
    Orders checkDoneOrder(Orders orders);
    Orders findPaidOrder(Long id, Long customerId);
}
