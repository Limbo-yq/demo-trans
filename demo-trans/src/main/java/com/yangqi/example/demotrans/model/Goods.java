package com.yangqi.example.demotrans.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "stock_num")
    private Long stockNum;
}
