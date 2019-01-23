package com.limbo.example.demodtxdbdb.service;

import com.limbo.example.demodtxdbdb.dao.OrderRepository;
import com.limbo.example.demodtxdbdb.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    @Qualifier("userJdbcTemplate")
    private JdbcTemplate userJdbcTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void updateOrder(Order order){
        userJdbcTemplate.update("update t_customer set deposit = deposit - ? where id = ?",order.getAmount(),order.getCustomerId());
        if(order.getTitle().contains("error1"))
            throw new RuntimeException("error1");
        orderRepository.save(order);
        if(order.getTitle().contains("error2"))
            throw new RuntimeException("error2");
    }


}
