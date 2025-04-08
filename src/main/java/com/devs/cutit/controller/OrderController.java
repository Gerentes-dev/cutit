package com.devs.cutit.controller;

import com.devs.cutit.model.OrderModel;
import com.devs.cutit.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/* :) */

@RestController
@RequestMapping("/orders")
@Tag(name = "Departure orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController (OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/search")
    public OrderModel searchPart(@RequestParam UUID id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/create")
    public OrderModel createPart(@RequestBody OrderModel orderModel) {
        return orderService.createOrder(orderModel);
    }

    @PutMapping("/edit")
    public OrderModel editPart(@RequestBody OrderModel orderModel) {
        return orderService.createOrder(orderModel);
    }

    @GetMapping("/all")
    public List<OrderModel> searchAllOrders() {
        return orderService.getAllOrders();
    }
}
