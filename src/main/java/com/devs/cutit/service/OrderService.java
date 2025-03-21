package com.devs.cutit.service;

import com.devs.cutit.model.OrderModel;
import com.devs.cutit.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderModel createOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    public OrderModel getOrder(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }
}