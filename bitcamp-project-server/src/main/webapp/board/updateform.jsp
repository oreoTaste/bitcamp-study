<%@page import="com.eomcs.lms.domain.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp" />

<div class='container'>
	<h1>게시글 수정</h1>

  <jsp:useBean id="board" class="com.eomcs.lms.domain.Board" scope="request"/>

	<%
	  if(board.getNo() == 0) {
  %>
	<p>해당 번호의 게시글이 없습니다.</p>
	<%
      } else {
  %>

	<form action='update' method='post'>
		번호:<input  name='no' type='text' value='${board.getNo()}' readonly><br>

		내용:<br>
		<textarea name='title' rows='5' cols='60'>${board.getTitle()}</textarea>
		<br> 
		등록일: ${board.getDate()}<br>
		조회수: ${board.getViewCount()}<br>
		<button>수정하기</button>
	</form>

	<%} %>
</div>

<jsp:include page="/footer.jsp" />