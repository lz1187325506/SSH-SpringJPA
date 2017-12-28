package com.hibernate.model;
/**
*@see Logger 日志记录
*@author eric
*@version 
*/

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Logger  {
    public Logger () {
        this.date = new Timestamp( System.currentTimeMillis());
    }
    private Timestamp date;
    private int id;
    private String msg;
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
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
    }
    /**
     * @return the date
     */
    public Timestamp getDate() {
        return date;
    }
    /**
     * @param date the date to set
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }
}