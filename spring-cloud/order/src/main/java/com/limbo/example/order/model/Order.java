package com.limbo.example.order.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private BigDecimal amount;

    private Date createTime;

    private String operateUser;


}
