<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>LMS 시스템</title>
<link rel='stylesheet'
  href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'
  integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh'
  crossorigin='anonymous'>
<style> 
body {
  background-color: #6c7b95;
}

h1{
style='font-weight: bold'
}
div.container {
  background-color: white;
  border: 1px solid white;
  width: 1000px;
}
</style>
</head>
<body>


  <nav class='navbar navbar-expand-lg navbar-dark bd-navbar' style='background-color: #464159;'>
    <a class='navbar-brand' href='index.jsp'>LMS 시스템</a>
    <button class='navbar-toggler' type='button' data-toggle='collapse'
      data-target='#navbarText' aria-controls='navbarText'
      aria-expanded='false' aria-label='Toggle navigation'>
      <span class='navbar-toggler-icon'></span>
    </button>
    <div class='collapse navbar-collapse' id='navbarText'>
      <ul class='navbar-nav mr-auto'>
        <li class='nav-item'>
          <a class='nav-link' href='board/list'>게시글 목록 보기</a></li>
        <li class='nav-item'>
          <a class='nav-link' href='lesson/list'>수업목록 보기</a></li>
        <li class='nav-item'>
          <a class='nav-link' href='member/list'>멤버목록 보기</a>
        <li class='nav-item active'>
      </ul>
      <% 
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if(loginUser!=null) {
      %>
      
      <span class='navbar-text' style="margin-right: 15px;">
      '<%=loginUser.getName()%>'님 환영
      </span>
      
      <button type="button" class="btn btn-outline-info" onclick="location.href='../auth/logout'">로그아웃</button>
      <%} else {%>
      <button type="button" class="btn btn-outline-info" onclick="location.href='../auth/login'">로그인</button>
      <%} %>
    </div>
  </nav>
  
  
	<br><br><br><br><br><br>
	<h1 class='bold' style='text-align: center'>안녕하세요!</h1>
	<h1 class='bold' style='text-align: center'>Hello!</h1>
  <h1 class='bold' style='text-align: center'>你好!</h1>
  <h1 class='bold' style='text-align: center; font-style: italic;'>Ciao!</h1>

  <script src='https://code.jquery.com/jquery-3.4.1.slim.min.js'
    integrity='sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n'
    crossorigin='anonymous'></script>
  <script
    src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js'
    integrity='sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo'
    crossorigin='anonymous'></script>
  <script
    src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js'
    integrity='sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6'
    crossorigin='anonymous'></script>
</body>
</html>
