package com.limbo.example.demodtxdbdb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_customer")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    private Integer amount;
}
