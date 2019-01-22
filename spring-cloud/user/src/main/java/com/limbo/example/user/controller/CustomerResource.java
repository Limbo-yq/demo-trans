package com.limbo.example.user.controller;

import com.limbo.example.dto.OrderDTO;
import com.limbo.example.user.client.OrderClient;
import com.limbo.example.user.model.Customer;
import com.limbo.example.user.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderClient orderClient;

    @PostConstruct
    private void init(){
        Customer customer = new Customer();
        customer.setUserName("limbo");
        customerService.createUser(customer);
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestParam String name){
        Customer customer = new Customer();
        customer.setUserName(name);
        return customerService.createUser(customer);
    }

    @GetMapping("/findAll")
    public List<Customer> findAllCustomer(){
        return customerService.findAll();
    }

    @GetMapping("/detailInfo")
    public Map getDetailInfo(){
        Customer customer = customerService.getCustomer("limbo");
        OrderDTO dto = orderClient.getOrder(1L);

        Map result = new HashMap();
        result.put("customer", customer);
        result.put("order", dto);

        return result;
    }

}
