package com.lhj.store.mapper;

import com.lhj.mapper.CartMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTest {
    @Autowired
    private CartMapper mapper;

    @Test
    public void delByPid(){
        Integer[] pid = new Integer[]{10000017,10000022};

        System.out.println(mapper.delByPid(pid,12));
    }

}
