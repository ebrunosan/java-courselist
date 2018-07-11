<%@page import="java.sql.*, java.net.*"%>
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
      Connection conn = null;
      ResultSet result = null;
      Statement stmt = null;

      try {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

        conn = DriverManager.getConnection(dbUrl, username, password);


        stmt = conn.createStatement();
        result = stmt.executeQuery("SELECT now()");

        if(result.next()) {
           out.write("<p>" + result.getString(1) + "</p>");
        }
 
        result.close();
        stmt.close();
		
	    try {
		  stmt = conn.createStatement();
		  stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
		  stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
		  ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

		  while (rs.next()) {
			out.write("<br>Read from DB: " + rs.getTimestamp(1));
		  }

		  out.write("<br>records", output);
		} catch (Exception e) {
		  out.write("<br>message", e.getMessage());
		}

		
		
        conn.close();
      }
      catch (Exception e) {
         System.out.println("Error " + e);
      }
    %>
</div>
</div>

<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>
</body>
</html>