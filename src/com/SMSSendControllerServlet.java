package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * Servlet implementation class SMSSendControllerServlet
 */

public class SMSSendControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SMSSendControllerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		try {
			RequestDispatcher requestDispatcher;
			String studentId = req.getParameter("sid");
			char classId = req.getParameter("class").charAt(0);
			char section = req.getParameter("section").charAt(0);
			String fname = req.getParameter("fname");
			String lname = req.getParameter("lname");
			String contact = req.getParameter("contact");
			String gender = req.getParameter("gender");
			System.out.println("GENDER :" + gender);
			String allSubjects = "";
			String[] subjects = req.getParameterValues("subjects");

			for (int i = 0; i < subjects.length; i++) {
				allSubjects = subjects[i] + "," + allSubjects;
			}
			if (allSubjects.charAt(allSubjects.length() - 1) == ',') {
				allSubjects = allSubjects.substring(0, allSubjects.length() - 1);
			}

			DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
			Query stQuery = new Query("Student");
			PreparedQuery pQuery = dataStore.prepare(stQuery);
			int count = pQuery.countEntities(FetchOptions.Builder.withDefaults());

			Entity student = new Entity("Student");

//			student.setProperty("Id", count + 1);
			student.setProperty("studentId", studentId);
			student.setProperty("class", classId + "");
			student.setProperty("section", section + "");
			student.setProperty("fname", fname);
			student.setProperty("lname", lname);
			student.setProperty("contact", contact);
			student.setProperty("gender", gender);
			student.setProperty("subjects", allSubjects);

			dataStore.put(student);
			requestDispatcher = getServletContext().getRequestDispatcher("/Success.html");
			requestDispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Message:" + e.getMessage());
		}

	}

}
