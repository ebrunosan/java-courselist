<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="_top-page.jsp" />

<div class="jumbotron text-center">
  <h1>Company</h1> 
  <p>We specialize in bla bla bla</p> 
</div>

<!-- Container (Services Section) -->
<div class="container-fluid text-center">
  <h2>SERVICES</h2>
  <h4>What we offer</h4>
  <br>
  <div class="row">
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-user"></span>
      <h4>USER CRUD</h4>
      <p>Create and mantain the system's ACL (Access Control List)</p>
	  <a href="<%= response.encodeURL( "user" ) %>"><button class="btn btn-default btn-lg">User</button></a>
	  <br />
    </div>

    <div class="col-sm-4">
      <span class="glyphicon glyphicon-education"></span>
      <h4>STUDENTS CRUD</h4>
      <p>Create and mantain students and its courses</p>
	  <a href="<%= response.encodeURL( "student" ) %>"><button class="btn btn-default btn-lg">Student</button></a>
	  <br />
    </div>

    <div class="col-sm-4">
      <span class="glyphicon glyphicon-road"></span>
      <h4>COURSE CRUD</h4>
      <p>Create and mantain the courses database</p>
	  <a href="<%= response.encodeURL( "course" ) %>"><button class="btn btn-default btn-lg">Course</button></a>
	  <br />
    </div>
  </div>
</div>

<jsp:include page="_botton-page.jsp" />
