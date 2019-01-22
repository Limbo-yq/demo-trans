package com.limbo.example.demodtxdbdb.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

    private Long id;

    private Long customerId;

    private String title;

    private Integer amount;
}
