package com.example.demo.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.OnDelete;
@Entity
@Table
@OnDelete(action = OnDeleteAction.CASCADE)
public class Users {
	@Id
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
			)
    @GeneratedValue(
    		strategy = GenerationType.SEQUENCE,
    		generator = "user_sequence"
    		)
  private long id;	
  private String name;
  private long phoneNumber;
  private String email;
  private String address;

public Users(String email, String password) {
	super();
	this.email = email;
	this.password = password;
}
private String password;
  private boolean isAdmin;
  private int wallet;
  
public Users() {
	super();
}
public Users(String name, long id, long phoneNumber, String email, String address, String password, boolean isAdmin,
		int wallet) {
	super();
	this.name = name;
	this.id = id;
	this.phoneNumber = phoneNumber;
	this.email = email;
	this.address = address;
	this.password = password;
	this.isAdmin = isAdmin;
	this.wallet = wallet;
}
public Users(String name, long phoneNumber, String email, String address, String password, boolean isAdmin, int wallet) {
	super();
	this.name = name;
	this.phoneNumber = phoneNumber;
	this.email = email;
	this.address = address;
	this.password = password;
	this.isAdmin = isAdmin;
	this.wallet = wallet;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public long getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(long phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
 
public boolean isAdmin() {
	return isAdmin;
}
public void setAdmin(boolean isAdmin) {
	this.isAdmin = isAdmin;
}
public int getWallet() {
	return wallet;
}
public void setWallet(int wallet) {
	this.wallet = wallet;
}
@Override
public String toString() {
	return "Users [name=" + name + ", id=" + id + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address="
			+ address + ", password=" + password + ", isAdmin=" + isAdmin + ", wallet=" + wallet + "]";
}
 
  
}
