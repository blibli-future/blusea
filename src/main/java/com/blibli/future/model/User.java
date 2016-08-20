package com.blibli.future.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String fullName;
	private String nickName;
	
	protected User() {}
	
	public User(String fullName, String nickName) 
	{
		this.fullName = fullName;
		this.nickName = nickName;
	}
	
	@Override
	public String toString()
	{
		return String.format(
                "Customer[id=%d, fullName='%s', nickName='%s']",
                id, fullName, nickName);
	}
}
