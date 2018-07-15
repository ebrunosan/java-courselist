<%@page import="com.sendgrid.*, java.io.IOException"%>
<!doctype html>

<html>
<head>
  <meta charset="utf-8">
  <title>DB Example</title>

  <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <link href="//netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container" id="getting-started">

<div class="page-header">
  <h1>Hello #Lambton Team</h1>
</div>

<h1>This was get from DB:</h1>
<div class="hero-unit">
    <%
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
	  System.out.println(response.statusCode);
	  System.out.println(response.body);
	  System.out.println(response.headers);
	} catch (IOException ex) {
	  throw ex;
	}
    %>
</div>
</div>

<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>
</body>
</html>