package com.limbo.example.user.client;

import com.limbo.example.dto.OrderDTO;
import com.limbo.example.iface.OrderInterFace;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "order",path = "/api/order")
public interface OrderClient extends OrderInterFace {

    @GetMapping("/{id}")
    OrderDTO getOrder(@PathVariable(name = "id") Long id);
}
