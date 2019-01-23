package com.limbo.example.demodtxdbdb.dao;

import com.limbo.example.demodtxdbdb.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
