<%@ page import="com.eomcs.lms.domain.Board"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp" />

<div class='container'>
	<h1>게시글</h1>
	<a href='add'>새글</a><br>
	<table border='1'>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>

    <jsp:useBean id="list" type="java.util.List<Board>" class="java.util.ArrayList" scope="request"/>

		<%
			for(Board item : list) {
			  pageContext.setAttribute("item", item);
			%>
		<tr>
			<td><a href='detail?no=${item.getNo()}'>${item.getNo()}</a></td>
			<td><a href='detail?no=${item.getNo()}'>${item.getTitle()}</a></td>
			<td><a href='detail?no=${item.getNo()}'>${item.getDate()}</a></td>
			<td><a href='detail?no=${item.getNo()}'>${item.getViewCount()}</a></td>
		</tr>
		<%
			}
			%>
	</table>

</div>

<jsp:include page="/footer.jsp" />