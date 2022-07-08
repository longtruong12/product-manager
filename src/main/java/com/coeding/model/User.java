package com.coeding.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String email;
	private String password;
	private List<String> roles;
	
	public User() {}

	public User(String email, String password, String... roles) {
		super();
		this.email = email;
		this.password = password;
		
		this.roles = new ArrayList<String>();
	      if (roles != null) {
	         for (String r : roles) {
	            this.roles.add(r);
	         }
	      }
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

   public List<String> getRoles() {
      return roles;
   }

   public void setRoles(List<String> roles) {
      this.roles = roles;
   }
}
