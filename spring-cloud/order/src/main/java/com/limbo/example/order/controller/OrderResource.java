package com.limbo.example.order.controller;

import com.limbo.example.dto.OrderDTO;
import com.limbo.example.iface.OrderInterFace;
import com.limbo.example.order.model.Order;
import com.limbo.example.order.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderResource implements OrderInterFace {

    @PostConstruct
    private void init(){
        Order order = new Order();
        order.setTitle("my demo order!");
        order.setAmount(new BigDecimal(1000));
        order.setCreateTime(new Date());
        order.setOperateUser("Admin");
        orderService.createOrder(order);
    }

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public Order addCustomer(@RequestParam String title){
        Order order = new Order();
        order.setTitle(title);
        return orderService.createOrder(order);
    }

    @HystrixCommand
    @GetMapping("/findAll")
    public List<Order> findAllCustomer(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable Long id){
        Order order = orderService.getOrder(id);
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setAmount(order.getAmount());
        dto.setTitle(order.getTitle());
        return dto;
    }

}
