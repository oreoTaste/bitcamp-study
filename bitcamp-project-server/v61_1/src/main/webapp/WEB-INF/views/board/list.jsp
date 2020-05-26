<%@ page import="com.eomcs.lms.domain.Board"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='container'>
	<h1>게시글</h1>
	<a href='form'>새글</a><br>
	<table border='1'>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>

<c:forEach items="${list}" var="item">
  <tr>
    <td><a href='detail?no=${item.getNo()}'>${item.getNo()}</a></td> 
    <td><a href='detail?no=${item.getNo()}'>${item.getTitle()}</a></td> 
    <td><a href='detail?no=${item.getNo()}'>${item.getDate()}</a></td> 
    <td><a href='detail?no=${item.getNo()}'>${item.getViewCount()}</a></td>
  </tr>
</c:forEach>
</table>
</div>
