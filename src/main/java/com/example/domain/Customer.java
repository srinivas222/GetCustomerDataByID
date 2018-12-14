package com.example.domain;

public class Customer {
	
	private int cid;
	private String cname;
	private String email;
	private String location;
	public Customer(int cid, String cname, String email, String location) {
		this.cid=cid;
		this.cname=cname;
		this.email=email;
		this.location=location;
		
	}
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + "]";
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}



}
