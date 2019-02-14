package com.gdei.hsearch.entity;

import com.gdei.hsearch.HsearchApplicationTests;
import com.gdei.hsearch.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//继承主测试类
public class UserRepositoryTest extends HsearchApplicationTests {
    @Autowired
    private UserRepository userRepository;

    //这里是使用MySQL的数据进行测试
    @Test
    public void testFineOne(){
        User user = userRepository.findOne(1L);
        Assert.assertEquals("wali", user.getName());

    }

}
