package com.yangqi.example.demotrans.repository;

import com.yangqi.example.demotrans.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
