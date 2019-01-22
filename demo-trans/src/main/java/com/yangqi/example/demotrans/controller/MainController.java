package com.yangqi.example.demotrans.controller;

import com.yangqi.example.demotrans.model.Order;
import com.yangqi.example.demotrans.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/main")
public class MainController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping(path = "/hello")
    public String hello(){
        return "Hello World";
    }

    @PostMapping(path = "/order")
    @Transactional
    public void orderGoods(@RequestParam String userName,
                           @RequestParam Long goodsId) throws Exception{
        Order order = new Order();
        order.setUserName(userName);
        order.setGoodsId(goodsId);

//        orderService.createOrder(order);
        orderService.createOrderTransInCode(order);

//        jmsTemplate.convertAndSend("goods:msg:handle",userName);
    }

    @GetMapping(path = "/findOrder")
    public List<Order> findOrder(){
        return orderService.findAll();
    }


}
