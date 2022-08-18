package com.lhj.controller;


import com.lhj.entity.Order;
import com.lhj.entity.OrderItem;
import com.lhj.service.IOrderService;
import com.lhj.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        // 从Session中取出uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行业务
        Order data = orderService.create(aid, cids, uid, username);
        // 返回成功与数据
        return new JsonResult<Order>(OK, data);
    }

    @RequestMapping("getOrder")
    public JsonResult<List<Order>> getOrder(HttpSession session) {
        // 从Session中取出uid和username
        List<Order> orderByUid = orderService.getOrderByUid(getUidFromSession(session));
        // 返回成功与数据
        return new JsonResult<List<Order>>(OK, orderByUid);
    }

    @RequestMapping("getOrderItem/{oid}")
    public JsonResult<List<OrderItem>> getOrderItem(@PathVariable("oid") Integer oid) {
        List<OrderItem> itemByOid = orderService.getItemByOid(oid);
        // 返回成功与数据
        return new JsonResult<List<OrderItem>>(OK, itemByOid);
    }


}
