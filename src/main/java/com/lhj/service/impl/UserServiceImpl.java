package com.lhj.service.impl;

import com.lhj.entity.User;
import com.lhj.mapper.UserMapper;
import com.lhj.service.IUserService;
import com.lhj.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        User byUserName = userMapper.findByUserName(user.getUsername());
        if (byUserName != null) {
            //抛出异常
            throw new UsernameDuplicateException("用户名被占用");
        }
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        user.setModifiedTime(new Date());
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在用户注册过程中产生了未知异常");
        }


    }

    @Override
    public User login(String username, String password) {
        User byUserName = userMapper.findByUserName(username);
        if (byUserName == null) {
            throw new UserNotFoundException("用户数据不存在");
        }

        if (byUserName.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        //检测用户密码是否匹配
        //1.先获取到数据库中的加密之后的密码
        String password1 = byUserName.getPassword();
        String salt = byUserName.getSalt();
        String md5Password = getMd5Password(password, salt);
        if (!password1.equals(md5Password)) {
            throw new PasswordNotMatchException("用户密码错误");
        }
        return byUserName;

    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User byUid = userMapper.findByUid(uid);
        if (byUid == null || byUid.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        //原始密码和数据库密码进行比较
        String md5Password = getMd5Password(oldPassword, byUid.getSalt());
        if (!md5Password.equals(byUid.getPassword())) {
            throw new PasswordNotMatchException("密码错误");
        }

        //将新的密码设置到数据库,将新的密码进行加密再更新
        String newMd5Password = getMd5Password(newPassword, byUid.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());

        if (rows != 1) {
            throw new UpdateException("更新数据产生未知异常");
        }


    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User byUid = userMapper.findByUid(uid);
        if (byUid == null || byUid.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer integer = userMapper.updateInfoByUid(user);
        if (integer != 1) {
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User byUid = userMapper.findByUid(uid);
        if (byUid == null || byUid.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        return byUid;
    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User byUid = userMapper.findByUid(uid);
        if (byUid == null || byUid.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows!=1) {
            throw new UpdateException("更新用户头像产生的未知异常");
        }
    }

    /**
     * 执行密码加密
     *
     * @param password 原始密码
     * @param salt     盐值
     * @return 加密后的密文
     */
    private String getMd5Password(String password, String salt) {
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
