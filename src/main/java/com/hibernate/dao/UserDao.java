package com.hibernate.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.model.User;
/**
 * UserDAO
 */

public interface UserDao extends JpaRepository<User,Serializable> {
    default boolean existUser(User user){
        return false;
    }

    User findById(int id);
   default public User loadUser(int id) {
       System.out.println(id);
       throw  new RuntimeException("runtimexception");
    }

}