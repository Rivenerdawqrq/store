package com.lhj.controller;


import com.lhj.entity.Address;
import com.lhj.service.IAddressService;
import com.lhj.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        // 调用业务对象的方法执行业务
        addressService.addNewAddress(uid, username, address);
        // 响应成功
        return new JsonResult<Void>(OK);
    }

    @GetMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<List<Address>>(OK, data);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.setDefault(aid, uid, username);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.delete(aid, uid, username);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/showAddress/{aid}")
    public JsonResult<Address> getByAid(@PathVariable("aid") Integer aid, HttpSession session,ModelAndView model){
        Address byAid = addressService.getByAid(aid, getUidFromSession(session));
        return new JsonResult<>(OK,byAid);

    }

    @RequestMapping("/editAddress")
    public JsonResult<Void> editAddress(Address address,HttpSession session){
        address.setModifiedUser(getUsernameFromSession(session));
        Integer integer = addressService.editByAid(address);
        System.out.println(address.getAid());
        System.out.println(integer);
        if (integer==1) {
            return new JsonResult<>(OK);
        }
        return new JsonResult<>(500);
    }

}

