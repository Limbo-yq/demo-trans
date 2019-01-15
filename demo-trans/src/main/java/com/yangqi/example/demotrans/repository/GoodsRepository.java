package com.yangqi.example.demotrans.repository;

import com.yangqi.example.demotrans.model.Goods;
import com.yangqi.example.demotrans.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {


}
