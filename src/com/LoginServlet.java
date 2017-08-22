package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RequestDispatcher requestDispatcher;
		String user = "", pwd = "";
		boolean isUser = false;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
//		Entity login = new Entity("Login", 1);
//		login.setProperty("LoginId", 1);
//		login.setProperty("username", username);
//		login.setProperty("password", password);
//		ds.put(login);
		Query query = new Query("Login");

		PreparedQuery pQuery = ds.prepare(query);
		for (Entity e : pQuery.asIterable()) {
			user = e.getProperty("username").toString();
			pwd = e.getProperty("password").toString();
			if (user.equals(username) && pwd.equals(password)) {
				isUser = true;
				requestDispatcher = getServletContext().getRequestDispatcher("/index.html");
				requestDispatcher.forward(req, res);
				break;
			}
		}
		if (!isUser) {
			requestDispatcher = getServletContext().getRequestDispatcher("/Error.html");
			requestDispatcher.forward(req, res);
		}
		// Entity login = new Entity("Login",1);
		// login.setProperty("LoginId",1);
		// login.setProperty("username",username);
		// login.setProperty("password",password);
		// ds.put(login);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// try {
		// LoginDAO loginDao = new LoginDAO();
		// String username = req.getParameter("username");
		// String password = req.getParameter("password");
		//
		// if (loginDao.verifyUser(username, password)) {
		// res.sendRedirect("sendSMS");
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// System.out.println("Message:" + e.getMessage());
		// }
	}

}
