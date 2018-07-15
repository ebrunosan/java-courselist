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
			
			// TODO get email address from DB
			String emailToAddr = "ebrunosan@gmail.com";
			
			Email from = new Email("app101305838@heroku.com");
			String subject = "Hello World from the SendGrid Java Library!";
			Email to = new Email(emailToAddr);
			Content content = new Content("text/plain", "Hello, Email!");
			Mail mail = new Mail(from, subject, to, content);
			
			request.setBody(mail.build());
			
			Response response = sg.api(request);
			
			PrintWriter out = res.getWriter();
			out.print("<html>");
			out.write("<p>E-mail sent to [" + emailToAddr + "]. Status code returned [" + response.getStatusCode() + "]");
			
			System.out.println("[SUCCESS] E-mail sent to [" + emailToAddr + "]. Status code returned [" + response.getStatusCode() + "]");
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}
 	}
}
