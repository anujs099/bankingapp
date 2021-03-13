package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.Registration;

/**
 * Servlet implementation class checkbalance
 */
@WebServlet("/checkbalance")
public class checkbalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkbalance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession();
		int accountid=(int) session.getAttribute("accountid");
		System.out.println(accountid);
		System.out.println("this is just a test");
		
		Session sessionhibernate=FactorySession.configUtil();
		Registration registration = sessionhibernate.get(Registration.class, accountid);
		int currentbalance=registration.getBalance();
		request.setAttribute("currentbalance", currentbalance);
		request.getRequestDispatcher("/WEB-INF/currentbalance.jsp").forward(request,response);
	}

}
