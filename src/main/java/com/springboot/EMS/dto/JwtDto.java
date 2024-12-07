package com.springboot.EMS.dto;

import org.springframework.stereotype.Component;
@Component
public class JwtDto {
	private String username;
	private String token;
	private int userid;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
}
