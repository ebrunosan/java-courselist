<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="/auth/_top-page.jsp" />

<div class="jumbotron text-center">
  <h1>BLB Company</h1> 
  <p>We specialize in Software Development. Hire us!</p> 
  <form class="form-inline">
    <div class="input-group">
      <input type="email" class="form-control" size="50" placeholder="Email Address" required>
      <div class="input-group-btn">
        <a href="" onclick="return alert('Thank you for your interest! Action is under construction.')"><button type="button" class="btn btn-danger">Subscribe</button></a>
      </div>
    </div>
  </form>
</div>

<!-- Container (About Section) -->
<div class="container-fluid text-center">
  <h1>About Company Page</h1>
  
  <h3>Mission</h3>
  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla pulvinar, tortor vel pharetra vehicula, neque leo tempor augue, sed viverra tortor nisl quis ligula. Vestibulum magna tortor, iaculis id libero eget, bibendum euismod lorem. In a ex quis mi fringilla aliquet. Praesent maximus mattis blandit. Curabitur convallis malesuada leo, quis blandit sapien sodales vel. Morbi fermentum aliquet urna ac mattis. Phasellus quis nunc nunc. Nam mollis auctor velit, sed elementum diam tempor ac. Etiam vulputate, arcu in porttitor dapibus, elit felis tincidunt elit, in cursus massa augue in sem. Maecenas vel dolor vitae lacus mollis mollis. Mauris vel dignissim mauris. Donec vulputate urna in sollicitudin lacinia. Curabitur pulvinar pharetra diam, eu porta justo. Integer maximus ac turpis nec laoreet. Cras condimentum lacinia tortor, a finibus nunc scelerisque non.</p>
  
  <h3>Values</h3>
  <p>Fusce eu nulla eu enim laoreet dignissim. Pellentesque ornare, nisi in dapibus blandit, ante leo feugiat lectus, non molestie odio diam quis justo. Vivamus interdum eu felis ut posuere. Praesent lobortis feugiat enim aliquet cursus. Suspendisse tristique consectetur tellus. Pellentesque vulputate mattis condimentum. Ut eu luctus urna, vitae sagittis felis. Integer augue turpis, porta vel turpis vel, vestibulum iaculis metus. Morbi mollis elit laoreet blandit pharetra. Quisque at sem id enim facilisis imperdiet. Nulla bibendum libero et lectus aliquet imperdiet. Proin rutrum mi vulputate quam vehicula, id egestas leo placerat. Morbi sit amet mi ut tortor malesuada eleifend. Nam ut interdum urna.</p>
  
  <a href="/newuser.jsp"><button class="btn btn-default btn-lg">Sign up</button></a>
  <br />
</div>

<jsp:include page="/auth/_botton-page.jsp" />
