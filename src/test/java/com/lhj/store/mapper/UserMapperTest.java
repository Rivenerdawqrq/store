package com.lhj.store.mapper;

import com.lhj.entity.User;
import com.lhj.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
//表示启动这个单元测试类
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    public UserMapper userMapper;

    /**
     *
     */
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("张三1");
        user.setGender(22);
        user.setPassword("123124");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void updatePasswordByUid() {
        userMapper.updatePasswordByUid(9, "321", "管理员", new Date());

    }

    @Test
    public void findByUid() {
        System.out.println(userMapper.findByUid(9));

    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setPhone("17666012478");
        user.setGender(1);
        user.setEmail("1595606639@qq.com");
        user.setUid(12);

        Integer integer = userMapper.updateInfoByUid(user);
        System.out.println(integer);
    }

    @Test
    public void updateAvatarByUid() {
        Integer uid = 12;
        String avatar = "/upload/avatar.png";
        String modifiedUser = "超级管理员";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, modifiedTime);
        System.err.println("rows=" + rows);
    }
}
