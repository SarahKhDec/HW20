package org.example.HW20.service;

import org.example.HW20.entity.*;
import org.example.HW20.entity.enumeration.OrderStatus;
import org.example.HW20.exceptions.order.LessProposedPriceException;
import org.example.HW20.exceptions.order.OrderNotFoundException;
import org.example.HW20.exceptions.time.LessTimeException;
import org.example.HW20.exceptions.user.LessCreditException;
import org.example.HW20.repository.OrdersRepository;
import org.example.HW20.utils.LocalDateTimeValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;
    private final CustomerServiceImpl customerService;
    private final ExpertServiceImpl expertService;
    private final SubServiceServiceImpl subServiceService;
    private final LocalDateTimeValidation dateTimeValidation;

    @Override
    public Orders create(Orders orders) {
        Customer customer = customerService.findByEmail(orders.getEmail());
        SubService subService = subServiceService.findById(orders.getSubService().getId());
        LocalDateTime localDateTime = dateTimeValidation.validator(orders.getTime());
        if (orders.getProposedPrice() < subService.getBasePrice()) {
            throw new LessProposedPriceException("your bid price is lower than the sub service base price.");
        }
        orders.setCustomer(customer);
        orders.setSubService(subService);
        orders.setDateAndTime(localDateTime);
        return ordersRepository.save(orders);
    }

    @Override
    public List<Orders> findAllByExpertSuService(Orders orders) {
        Expert expert = expertService.findAcceptedExpertByEmail(orders.getEmail());
        return ordersRepository.findBySubService(expert.getSubServiceSet());
    }

    @Override
    public Orders findByIdAndStatus(Long id, Set<SubService> subServices) {
        return ordersRepository.findByIdAndOrderStatus(id, subServices).orElseThrow(
                () -> new OrderNotFoundException("there are no orders with this ID available for your sub service.")
        );
    }

    @Override
    public void updateOrderStatusToSelect(Orders order, Offers offers) {
        List<Offers> offersList = new ArrayList<>();
        offersList.add(offers);
        order.setOffers(offersList);
        order.setOrderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION);
        ordersRepository.save(order);
    }

    @Override
    public Orders findByIdAndCustomerId(Long id, Long customerId) {
        return ordersRepository.findByIdAndCustomerId(id, customerId).orElseThrow(
                () -> new OrderNotFoundException("you do not have any orders with the status Pending Expert Selection for this order ID.")
        );
    }

    @Override
    public Orders updateOrderStatusToComePlace(Orders orders, Offers offers) {
        orders.setOfferSelected(offers);
        orders.setOrderStatus(OrderStatus.WAITING_FOR_THE_SPECIALIST_TO_COME_TO_YOUR_PLACE);
        return ordersRepository.save(orders);
    }

    @Override
    public Orders updateOrderStatusToStarted(Orders orders) {
        Customer customer = customerService.findByEmail(orders.getEmail());
        Orders newOrder = ordersRepository.findByOrderStatusAndCustomerId(orders.getId(), customer.getId())
                .orElseThrow(() -> new OrderNotFoundException("you do not have any orders with the status of waiting for an expert to come to your place with this ID."));
        if (LocalDateTime.now().isBefore(newOrder.getOfferSelected().getSuggestedTime())) {
            throw new LessTimeException("you cannot change the status of order to 'Started' before it starts.");
        }
        newOrder.setOrderStatus(OrderStatus.STARTED);
        return ordersRepository.save(newOrder);
    }

    @Override
    public Orders updateOrderStatusToDone(Orders orders) {
        Customer customer = customerService.findByEmail(orders.getEmail());
        Orders newOrder = ordersRepository.findStartedOrder(orders.getId(), customer.getId())
                .orElseThrow(() -> new OrderNotFoundException("you do not have any orders with the status of started with this ID."));
        newOrder.setOrderStatus(OrderStatus.DONE);
        expertService.calculateExpertScore(newOrder.getOfferSelected().getExpert(), newOrder.getOfferSelected().getDurationOfWork());
        return ordersRepository.save(newOrder);
    }

    @Override
    public List<Orders> findAllDoneOrder(Orders orders) {
        Customer customer = customerService.findByEmail(orders.getEmail());
        return ordersRepository.findAllDoneOrder(customer.getId());
    }

    @Override
    public Orders checkDoneOrder(Orders orders) {
        Customer customer = customerService.findByEmail(orders.getEmail());
        return ordersRepository.findOrderForPayment(orders.getId(), customer.getId())
                .orElseThrow(() -> new OrderNotFoundException("you do not have any orders with the status of done with this ID"));
    }

    @Override
    public Orders paymentWithOneMethod(Orders orders) {
        Customer customer = customerService.findByEmail(orders.getEmail());
        Orders newOrder = checkDoneOrder(orders);
        if (customer.getCredit() < newOrder.getOfferSelected().getProposedPrice()) {
            throw new LessCreditException("your inventory is insufficient.");
        }
        Long money = (newOrder.getOfferSelected().getProposedPrice() * 70) / 100;
        newOrder.setOrderStatus(OrderStatus.PAID);
        ordersRepository.save(newOrder);
        expertService.updateExpertCredit(money, newOrder.getOfferSelected().getExpert());
        return newOrder;
    }

    @Override
    public Boolean paymentWithTwoMethod(Orders orders) {
        Orders newOrder = checkDoneOrder(orders);
        Long money = (newOrder.getOfferSelected().getProposedPrice() * 70) / 100;
        newOrder.setOrderStatus(OrderStatus.PAID);
        ordersRepository.save(newOrder);
        expertService.updateExpertCredit(money, newOrder.getOfferSelected().getExpert());
        return true;
    }

    @Override
    public Orders findPaidOrder(Long id, Long customerId) {
        return ordersRepository.findPaidOrder(id, customerId).orElseThrow(
                () -> new OrderNotFoundException("you do not have any orders with the status of paid with this ID")
        );
    }
}
