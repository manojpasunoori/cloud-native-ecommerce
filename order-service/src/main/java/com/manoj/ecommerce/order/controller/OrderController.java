package com.manoj.ecommerce.order.controller;

import com.manoj.ecommerce.order.model.Order;
import com.manoj.ecommerce.order.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List; // <--- Added this import
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderRepository orderRepository;


    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // 1. Place Order (The "IN" Door)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody Order order) {
        order.setOrderNumber(UUID.randomUUID().toString());
        orderRepository.save(order);
        return "Order Placed Successfully";
    }

    // 2. See Orders (The "OUT" Door) <-- We were missing this!
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}