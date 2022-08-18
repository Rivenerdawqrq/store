package com.lhj.store.mapper;

import com.lhj.entity.Order;
import com.lhj.entity.OrderItem;
import com.lhj.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTests {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void getOrderByUid(){
        for (Order order : orderMapper.getOrderByUid(12)) {
            System.out.println(order);
        }

    }

    @Test
    public void getOrderItemByOid(){
        for (OrderItem order : orderMapper.getItemByOid(1)) {
            System.out.println();
            System.out.println(order);
        }
    }
}
