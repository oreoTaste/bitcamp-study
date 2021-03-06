<%@page import="com.eomcs.lms.domain.Lesson"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<div class='container'>
	<h1>수업 정보 검색</h1>
	입력값 : ${keyword}
	<hr>
	
검색결과 : <br>
	<table border='1'>
		<tr>
		  <th>레슨번호</th>
		  <th>수업명</th>
		  <th>수업내용</th>
		  <th>시작일</th>
		  <th>종료일</th>
		  <th>총시간</th>
		  <th>일시간</th>
		</tr>
		
		<%
		List<Lesson> lesson = (List<Lesson>)request.getAttribute("lesson");
		
		for(Lesson ls : lesson) {
		%>
		
		<tr>
		  <td><a href='detail?no=${ls.getNo()}'>${ls.getNo()}</a></td>
		  <td><a href='detail?no=${ls.getNo()}'>${ls.getTitle()}</a></td>
		  <td><a href='detail?no=${ls.getNo()}'>${ls.getContext()}</a></td>
		  <td><a href='detail?no=${ls.getNo()}'>${ls.getStartDate()}</a></td>
		  <td><a href='detail?no=${ls.getNo()}'>${ls.getEndDate()}</a></td>
		  <td><a href='detail?no=${ls.getNo()}'>${ls.getTotalHour()}</a></td>
		  <td><a href='detail?no=${ls.getNo()}'>${ls.getDailyHour()}</a></td>
		</tr>
		
		<%
    }
		%>
		</table>
</div>
