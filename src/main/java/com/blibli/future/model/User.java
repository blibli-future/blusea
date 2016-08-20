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
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	private String email;
	
	public User () {}
	
	public User(String fullName, String nickName, String email) 
	{
		this.fullName = fullName;
		this.nickName = nickName;
		this.email    = email;
	}
	
	@Override
	public String toString()
	{
		return String.format(
                "Customer[id=%d, fullName='%s', nickName='%s']",
                id, fullName, nickName);
	}
}
