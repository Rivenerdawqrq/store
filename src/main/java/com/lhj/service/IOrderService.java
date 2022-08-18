package com.lhj.service;

import com.lhj.entity.Order;
import com.lhj.entity.OrderItem;

import java.util.List;

/** 处理订单和订单数据的业务层接口 */
public interface IOrderService {
    /**
     * 创建订单
     * @param aid 收货地址的id
     * @param cids 即将购买的商品数据在购物车表中的id
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 成功创建的订单数据
     */
    Order create(Integer aid, Integer[] cids, Integer uid, String username);


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
