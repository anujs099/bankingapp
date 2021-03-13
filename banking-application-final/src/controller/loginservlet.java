package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;


import pojo.Registration;


/**
 * Servlet implementation class loginservlet
 */
@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		
		int enteredid=Integer.parseInt(account);
		
		Session session=FactorySession.configUtil();
		Registration accountexist=session.get(Registration.class,enteredid);

	
		String storedpass=accountexist.getPassword();
		int balance=accountexist.getBalance();
		
		HttpSession sessionhttp=request.getSession();
		sessionhttp.setAttribute("accountid", enteredid);
		sessionhttp.setAttribute("balance", balance);
		
		int storedid=accountexist.getId();

		if(storedid==enteredid&&storedpass.equals(password)) {
			response.sendRedirect("homepage.html");
		}
		else {
			response.sendRedirect("loginfailure.html");
		}
	}
	


}
	
