package com.yangqi.example.demotrans.service;

import com.yangqi.example.demotrans.model.Goods;
import com.yangqi.example.demotrans.model.Order;
import com.yangqi.example.demotrans.repository.GoodsRepository;
import com.yangqi.example.demotrans.repository.OrderRepository;
import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Optional;

@Service(value = "orderService")
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private PlatformTransactionManager transManager;

    @Transactional
    public void createOrder(Order order) throws Exception {
        //生成订单
        orderRepository.save(order);

        //扣除库存
        Goods goods = goodsRepository.findById(order.getGoodsId()).get();
        if (goods.getStockNum() < 1) {
            throw new RuntimeException("库存不足1件");
//            throw new Error("严重错误");
        }
        goods.setStockNum(goods.getStockNum() - 1);
        goodsRepository.save(goods);
    }

    public void createOrderTransInCode(Order order) throws Exception {
        DefaultTransactionDefinition df = new DefaultTransactionDefinition();
        df.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        df.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus ts = transManager.getTransaction(df);

        try {
            //生成订单
            orderRepository.save(order);

            //扣除库存
            Goods goods = goodsRepository.findById(order.getGoodsId()).get();
            if (goods.getStockNum() < 1) {
                throw new RuntimeException("库存不足1件");
//            throw new Error("严重错误");
            }
            goods.setStockNum(goods.getStockNum() - 1);
            goodsRepository.save(goods);
            transManager.commit(ts);
        } catch (RuntimeException e) {
            transManager.rollback(ts);
            throw e;
        }
    }

}
