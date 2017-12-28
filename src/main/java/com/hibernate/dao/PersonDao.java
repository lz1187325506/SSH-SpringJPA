package com.hibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.model.Person;
import java.io.Serializable;

/**
 * PersonDao extends Jpa
 */
public interface PersonDao extends JpaRepository<Person,Serializable> {
    Person findById(int id);
}