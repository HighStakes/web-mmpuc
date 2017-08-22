package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
			String message = req.getParameter("message");
			
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
			sendMessage(fname+" "+lname,contact,message);
			requestDispatcher = getServletContext().getRequestDispatcher("/Success.html");
			requestDispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Message:" + e.getMessage());
		}

	}
	
	public static void sendMessage(String pname, String contact, String message) {
		try {
			message = URLEncoder.encode(message,"UTF-8");
			contact = contact.trim();
			String gupshupURL = "http://enterprise.smsgupshup.com/GatewayAPI/rest?method=SendMessage&send_to="+contact+"&msg="+message+"&msg_type=TEXT&userid=2000164615&auth_scheme=plain&password=b9gXWd&v=1.1&format=text";
			URL url = new URL(gupshupURL.trim());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.connect();
			System.out.println("URL Finally :" + url);
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				buffer.append(line).append("\n");
			}
			System.out.println(buffer.toString());
			rd.close();
			conn.disconnect();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
