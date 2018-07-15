package main.java;

import java.net.*;

import com.sendgrid.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	name = "TestEmailServlet",
	urlPatterns = "/test-email"
)

@SuppressWarnings("serial")
public class TestEmailServlet extends HttpServlet {

	public TestEmailServlet() {
		super();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
		  SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		  Request request = new Request();
		  request.setMethod(Method.POST);
		  request.setEndpoint("mail/send");
		  request.setBody("{\"personalizations\":[{\"to\":[{\"email\":\"ebrunosan@gmail.com\"}],\"subject\":\"Sending with SendGrid is Fun\"}],\"from\":{\"email\":\"app101305838@heroku.com\"},\"content\":[{\"type\":\"text/plain\",\"value\": \"and easy to do anywhere, even with Java\"}]}");
		  Response response = sg.api(request);
		  System.out.println("+++ EMAIL SENT !!! +++");
//		  System.out.println(response.getStatusCode());
//		  System.out.println(response.getBody());
//		  System.out.println(response.getHeaders());
		} catch (IOException ex) {
		  throw ex;
		}
 	}

/*
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Email from = new Email("app101305838@heroku.com");
		String subject = "Hello World from the SendGrid Java Library!";
		Email to = new Email("ebrunosan@gmail.com");
		Content content = new Content("text/plain", "Hello, Email!");
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();
		try {
			request.method = Method.POST;
			request.endpoint = "mail/send";
			request.body = mail.build();
			Response response = sg.api(request);

			PrintWriter out = res.getWriter();
			out.print("<html>");
			out.write("<p> Test email executed ...</p>");
//			out.write("<p>" + response.statusCode + "</p>");
//			out.write("<p>" + response.body + "</p>");
//			out.write("<p>" + response.headers + "</p>");
		} catch (Exception ex) {
		  throw ex;
		}
 	}
*/
}
