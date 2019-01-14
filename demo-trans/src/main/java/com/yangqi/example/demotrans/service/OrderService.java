package com.yangqi.example.demotrans.service;

import com.yangqi.example.demotrans.model.Order;
import com.yangqi.example.demotrans.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "orderService")
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void save(Order order){
        orderRepository.save(order);
    }
}
