package com.lhj.mapper;


import com.lhj.entity.Order;
import com.lhj.entity.OrderItem;

import java.util.List;

/** 处理订单及订单商品数据的持久层接口 */
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    /**
     * 查询订单
     * @param uid 用户id
     * @return 订单信息
     */
    List<Order> getOrderByUid(Integer uid);

    /**
     * 查询订单订单明细
     * @param oid 订单id
     * @return 订单明细
     */
    List<OrderItem> getItemByOid(Integer oid);
}
