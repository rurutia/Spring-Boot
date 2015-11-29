package main.java.com.haoyumichael.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@JsonPropertyOrder({"username", "user_password"})
public class Account {

	@Id
	@GeneratedValue
	long id;
	String username;
	
	@JsonProperty(value = "user_password")
	String password;
	
	boolean isBusiness;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonIgnore
	public boolean isBusiness() {
		return isBusiness;
	}
	public void setBusiness(boolean isBusiness) {
		this.isBusiness = isBusiness;
	}
		
}
