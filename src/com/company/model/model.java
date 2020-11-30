package com.company.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class model {
	Connection con;
	PreparedStatement pstmt;
	int x ;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private int accountid;
	private String balance;
	private String password;
	
	
	public model() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "mypass");
			System.out.println("connection");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public int register(){
		try {
			System.out.println("start registering");
			String sql="insert into new_table values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accountid);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.setString(4, balance);
			x= pstmt.executeUpdate();
			System.out.println(x);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return x;
	}
	
}
