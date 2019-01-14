package com.yangqi.example.demotrans.repository;

import com.yangqi.example.demotrans.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
