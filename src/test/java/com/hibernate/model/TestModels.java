package com.hibernate.model;
/**
*@see TestModels 测试DAO层
*@author eric
*@version 
*/

import com.config.SpringJPA;
import com.spring.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SpringJPA.class})
public class TestModels  {
    public TestModels () {}
   
    @Autowired
    private UserService userService;
    
    @Test
    public void testUserDao(){
        User user = new User();
        user.setUsername("王五");
        user.setPassword("1234545");
        userService.saveUser(user);
    }

    //延迟加载
    @Test
    public void testLazyLoad(){
        User user = userService.findById(7);
        System.out.println(user.getClassroom().getClassname());
    }
}