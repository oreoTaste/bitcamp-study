<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>LMS 시스템(관리자)</title>
<link rel='stylesheet'
  href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'
  integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh'
  crossorigin='anonymous'>
<style> 
body {
  background-color: #6c7b95;
}

input, textarea {
 background-color: #97a7c2;"
}

div.container {
  color: black;
  width: 1000px;
}

a:link, a:active { text-decoration:none; color:#450045;}
a:visited {text-decoration:none; color:#800080;}
a:hover { text-decoration:none; }

</style>
</head>
<body>


  <nav class='navbar navbar-expand-lg navbar-dark bd-navbar' style='background-color: #464159;'>
    <a class='navbar-brand' href='../../index.jsp'>LMS 시스템(관리자)</a>
    <button class='navbar-toggler' type='button' data-toggle='collapse'
      data-target='#navbarText' aria-controls='navbarText'
      aria-expanded='false' aria-label='Toggle navigation'>
      <span class='navbar-toggler-icon'></span>
    </button>
    <div class='collapse navbar-collapse' id='navbarText'>
      <ul class='navbar-nav mr-auto'>
        <li class='nav-item'>
          <a class='nav-link' href='../board/list'>게시글 목록 보기</a></li>
      </ul>
      <% 
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if(loginUser!=null) {
      %>
      <span class='navbar-text' style='margin-right: 15px;'>'<%=loginUser.getName()%>'님 환영</span>
      <button type="button" class="btn btn-outline-info" onclick="location.href='../auth/logout'">로그아웃</button>
      <%} else {%>
      <button type="button" class="btn btn-outline-info" onclick="location.href='../auth/form'">로그인</button>
      <%} %>
    </div>
  </nav>