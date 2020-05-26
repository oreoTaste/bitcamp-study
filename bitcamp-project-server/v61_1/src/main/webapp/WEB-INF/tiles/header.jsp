<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

  <nav class='navbar navbar-expand-lg navbar-dark bd-navbar' style='background-color: #464159;'>
    <a class='navbar-brand' href='../../index.jsp'>LMS 시스템</a>
    <button class='navbar-toggler' type='button' data-toggle='collapse'
      data-target='#navbarText' aria-controls='navbarText'
      aria-expanded='false' aria-label='Toggle navigation'>
      <span class='navbar-toggler-icon'></span>
    </button>
    <div class='collapse navbar-collapse' id='navbarText'>
      <ul class='navbar-nav mr-auto'>
        <li class='nav-item'>
          <a class='nav-link' href='../board/list'>게시글 목록 보기</a></li>
        <li class='nav-item'>
          <a class='nav-link' href='../lesson/list'>수업목록 보기</a></li>
        <li class='nav-item'>
          <a class='nav-link' href='../member/list'>멤버목록 보기</a>
        <li class='nav-item active'>
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