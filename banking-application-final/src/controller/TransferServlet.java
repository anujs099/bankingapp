package controller;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import pojo.Registration;
import transactionutility.QueryData;

/**
 * Servlet implementation class TransferServlet
 */
@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String ammounts = request.getParameter("ammount");
			int ammount = Integer.parseInt(ammounts);

			String accounts = request.getParameter("acccount");
			int benificaryAccount = Integer.parseInt(accounts);

			Session session = FactorySession.configUtil();

			Registration customer = session.get(Registration.class, benificaryAccount);

			HttpSession session1 = request.getSession();

			int userbalance = (int) session1.getAttribute("balance");
			int Useraccount = (int) session1.getAttribute("accountid");

			int currentBalance = (userbalance - ammount);

			int number = 0;
			if (currentBalance > -1) {
				if (customer != null) {
					number = QueryData.balanceQuery(currentBalance, Useraccount);
				} else {

					response.sendRedirect("errorquery.html");
				}
			} else {

				response.sendRedirect("insfuctiet.html");
			}
			if (number == 1) {

				int benfiBalance = customer.getBalance();
				int updateBalance = benfiBalance + ammount;
				String customerName = customer.getFirstname();

				request.setAttribute("username", customerName);
				int customerValue = QueryData.balanceQuery(updateBalance, benificaryAccount);

				if (customerValue == 1) {

					long transctionId = ThreadLocalRandom.current().nextLong(1000000000000L);
					QueryData.transcationQuery(ammount, benificaryAccount, Useraccount, transctionId);
					request.getRequestDispatcher("/WEB-INF/transactional.jsp").forward(request, response);

				} else {
					response.sendRedirect("transcationfaliure.html");
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
