package com.limbo.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderDTO {

    private Long id;

    private String title;

    private BigDecimal amount;
}
