package controller;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Registration;

/**
 * Servlet implementation class registerservlet
 */
@WebServlet("/registerservlet")
public class registerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int balance=20000;
		String firstname=request.getParameter("firstname");
		String password=request.getParameter("password");
		String val=request.getParameter("answer");
		String email=request.getParameter("email");
		String date=request.getParameter("date");
		String address=request.getParameter("Address");
		String branch=request.getParameter("branch");
			
		int account=ThreadLocalRandom.current().nextInt(1000000000);
	
		Registration reg1=new Registration();
		reg1.setAddress(address);
		reg1.setBranch(branch);
		reg1.setDate(date);
		reg1.setEmail(email);
		reg1.setFirstname(firstname);
		reg1.setPassword(password);
		reg1.setVal(val);
		reg1.setId(account);
		reg1.setBalance(balance);
		
		Session session=FactorySession.configUtil();
		 Transaction transaction=session.beginTransaction();
		 
		 int pkid=(int) session.save(reg1);
	     
	     transaction.commit();
	     
	     if (pkid==reg1.getId()) {
	 		
	    	 response.sendRedirect("RegistrationSuccess.html");
		}
	     else {
			response.sendRedirect("FaliureRegister.html");
		}
	}

}
