package com.company.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.model.model;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String accountid=request.getParameter("accountid");
		int actual=Integer.parseInt(accountid);
		String balance=request.getParameter("balance");
		String password=request.getParameter("password");
		model m=new model();
		m.setAccountid(actual);
		m.setBalance(balance);
		m.setName(name);
		m.setPassword(password);
		
		int result=m.register();
		if(result==1) {
			response.sendRedirect("successfulentry.html");
		}
		else {
			response.sendRedirect("error404.html");
		}

	}
}
