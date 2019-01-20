package com.limbo.example.order.service;

import com.limbo.example.order.model.Order;
import com.limbo.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order getOrder(Long id){
        return orderRepository.findById(id).get();
    }
}
