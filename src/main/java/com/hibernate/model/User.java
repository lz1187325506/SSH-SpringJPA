package com.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



/**
*@see User 数据结构表单元java
*@author eric
*@version 
*/
@Entity
public class User  {
	public User () {}
	public User (String username){
		this.username = username;
	}
	public User(String username,String  password){
		this.username= username;
		this.password= password;
	}
    private int id;
	private String username;
	private String password;
	private ClassRoom classroom;
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the classroom
	 */
	@ManyToOne(cascade=CascadeType.ALL ,fetch=FetchType.LAZY)
	@JoinColumn(name="class_id")
	public ClassRoom getClassroom() {
		return classroom;
	}
	/**
	 * @param classroom the classroom to set
	 */
	public void setClassroom(ClassRoom classroom) {
		this.classroom = classroom;
	}
}