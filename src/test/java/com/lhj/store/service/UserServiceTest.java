package com.lhj.store.service;

import com.lhj.entity.User;
import com.lhj.mapper.UserMapper;
import com.lhj.service.IUserService;
import com.lhj.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
//表示启动这个单元测试类
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    public IUserService userService;
    /**
     *
     */
    @Test
    public void reg(){

        try {
            User user = new User();
            user.setUsername("test002");
            user.setGender(22);
            user.setPassword("123");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        User zhangsan = userService.login("张三333", "123");
        System.out.println(zhangsan);
    }


    @Test
    public void changePassword(){
        userService.changePassword(11,"管理员","123","321");
    }

    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("17111");
        user.setGender(0);
        user.setEmail("11123456");
        userService.changeInfo(12,"管理员",user);
    }

    @Test
    public void getByUid(){
        User byUid = userService.getByUid(12);
        System.out.println(byUid);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(12,"管理员","/upload");

    }
}
