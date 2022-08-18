package com.lhj.service;

import com.lhj.entity.User;
import org.springframework.stereotype.Service;

public interface IUserService {
    //用户注册方法
    void reg(User user);

    //登录
    User login(String username,String password);

    //修改密码
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    void changeInfo(Integer uid,String username,User user);

    User getByUid(Integer uid);

    /**
     * 修改用户头像
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @param avatar 用户的新头像的路径
     */
    void changeAvatar(Integer uid, String username, String avatar);



}
