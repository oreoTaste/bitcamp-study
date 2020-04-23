<%@page import="java.sql.Date"%>
<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page import="com.eomcs.lms.domain.Board"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<jsp:include page="../header.jsp" />

<div class='container'>

	<h1>멤버 목록</h1>

	<div>
		<form action='search'>
			<input name='keyword' type=text size='70' value='검색할 멤버'>
			<button>검색</button>
		</form>
	</div>

	<a href='form'>멤버 추가</a><br>

	<table border='1'>
		<tr>
			<th>멤버번호</th>
			<th>성함</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>등록일</th>
		</tr>

		<%
		  List<Member> members = (List<Member>) request.getAttribute("list");
		for (Member item : members) {
		%>
		<tr>
			<td><a href='detail?no=<%=item.getNo()%>'><%=item.getNo()%></a></td>
			<td><a href='detail?no=<%=item.getNo()%>'><%=item.getName()%></a></td>
			<td><a href='detail?no=<%=item.getNo()%>'><%=item.getEmail()%></a></td>
			<td><a href='detail?no=<%=item.getNo()%>'><%=item.getEmail()%></a></td>
			<td><a href='detail?no=<%=item.getNo()%>'><%=item.getRegisteredDate()%></a></td>
		</tr>
		<%
      }
      %>

	</table>
</div>

<jsp:include page="../footer.jsp" />