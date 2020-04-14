<%@page import="com.eomcs.lms.domain.Member"%>
<%@page import="com.eomcs.lms.domain.Lesson"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp" />

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
		List<Member> member = (List<Member>)request.getAttribute("member");
		
		for(Member m : member) {
		%>
		
		<tr>
		  <td><a href='detail?no=${m.getNo()}'>${m.getNo()}</a></td>
		  <td><a href='detail?no=${m.getNo()}'>${m.getTitle()}</a></td>
		  <td><a href='detail?no=${m.getNo()}'>${m.getContext()}</a></td>
		  <td><a href='detail?no=${m.getNo()}'>${m.getStartDate()}</a></td>
		  <td><a href='detail?no=${m.getNo()}'>${m.getEndDate()}</a></td>
		  <td><a href='detail?no=${m.getNo()}'>${m.getTotalHour()}</a></td>
		  <td><a href='detail?no=${m.getNo()}'>${m.getDailyHour()}</a></td>
		</tr>
		
		<%
    }
		%>
		</table>
</div>

<jsp:include page="/footer.jsp" />