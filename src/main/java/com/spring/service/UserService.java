package com.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hibernate.dao.UserDao;
import com.hibernate.model.User;

/**
*@see UserService 
*@author eric
*@version 
*/
@Service("userService")
public class UserService  {
    public UserService () {}
    @Autowired
    UserDao userDao;
    @Transactional
    public User saveUser(User user){
        User savedUser = userDao.save(user);
        // userDao.loadUser(2);
       return savedUser;
    }
    public User findById(int id){
        return userDao.findById(id);
    }
}