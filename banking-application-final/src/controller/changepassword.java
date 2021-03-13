package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Registration;



/**
 * Servlet implementation class changepassword
 */
@WebServlet("/changepassword")
public class changepassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changepassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountid=request.getParameter("accountid");
		int enteredaccountid=Integer.parseInt(accountid);
		String newpassword=request.getParameter("newpassword");
		String oldpassword=request.getParameter("oldpassword");
		String confirmpassword=request.getParameter("confirmpassword");
		
		HttpSession session2 = request.getSession();
		int accountidlogin=(int)session2.getAttribute("accountid");
		Session session=FactorySession.configUtil();
		Transaction transaction=null;
		if(enteredaccountid==accountidlogin) {
			Registration registereduser=session.get(Registration.class, accountidlogin);
			try {
				if(newpassword.equals(confirmpassword)) {
					transaction=session.beginTransaction();
					registereduser.setPassword(newpassword);
					session.saveOrUpdate(registereduser);
					transaction.commit();
					response.sendRedirect("login.html");
				}
				else {
					response.sendRedirect("passwordchangefailure.html");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				transaction.rollback();
				e.printStackTrace();
			}
		}
	}

}
