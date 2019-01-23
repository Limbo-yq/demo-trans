package com.limbo.example.demodtxdbdb.web;

import com.limbo.example.demodtxdbdb.model.Order;
import com.limbo.example.demodtxdbdb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/order")
    public String createOrder(@RequestBody Order order) {
        try {
            customerService.updateOrder(order);
        } catch (Exception e) {
            return "ERROR";
        }
        return "SUCCESS";
    }
}
