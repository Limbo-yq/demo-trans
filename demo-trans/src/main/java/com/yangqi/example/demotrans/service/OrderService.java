package com.yangqi.example.demotrans.service;

import com.yangqi.example.demotrans.model.Goods;
import com.yangqi.example.demotrans.model.Order;
import com.yangqi.example.demotrans.repository.GoodsRepository;
import com.yangqi.example.demotrans.repository.OrderRepository;
import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Optional;

@Service(value = "orderService")
public class OrderService {
    public static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private JmsTemplate jmsTemplate;


    public void createOrder(Order order) throws Exception {
        log.info("user:{} start order...", order.getUserName());
        //生成订单
        orderRepository.save(order);
//        jmsTemplate.convertAndSend("goods:msg:handle",order.getGoodsId());
//        //减少库存
//        dealStock(order.getGoodsId());
    }

    public void createOrderTransInCode(Order order) throws Exception {
        DefaultTransactionDefinition df = new DefaultTransactionDefinition();
//        df.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
//        df.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus ts = transactionManager.getTransaction(df);

        try {
            //生成订单
            orderRepository.save(order);
            //减少库存
            jmsTemplate.convertAndSend("goods:msg:handle", order.getGoodsId());
            transactionManager.commit(ts);
        } catch (RuntimeException e) {
            transactionManager.rollback(ts);
            throw e;
        }
    }

    @JmsListener(destination = "goods:msg:handle")
    @Transactional
    public void createOrder(String userName) {
        Order order = new Order();
        order.setUserName(userName);
        orderRepository.save(order);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    //    @JmsListener(destination = "goods:msg:handle")
    @Transactional
    public void dealStock(Long goodsId) throws RuntimeException {
        log.info("goodsId:{} start deal stock...", goodsId);
        //扣除库存
        Goods goods = goodsRepository.findById(goodsId).get();
        if (goods.getStockNum() < 1) {
            throw new RuntimeException("库存不足1件");
//            throw new Error("严重错误");
        }
        goods.setStockNum(goods.getStockNum() - 1);
        goodsRepository.save(goods);
    }

}
