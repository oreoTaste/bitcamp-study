<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp"/>

<div class='container'>
<h1>강의 등록(JSP)</h1>

  <form action='add' method='post'>
	강의명: <input name='title' type='text'><br>
	내용:<br>
	<textarea name='context' rows='5' cols='60'></textarea><br>
	강의 시작일: <input name='startDate' type='date'><br>
	강의 종료일: <input name='endDate' type='date'><br>
	총 강의시간: <input name='totalHour' type='number'><br>
	일 강의시간: <input name='dailyHour' type='number'><br>
	<button>제출하기</button>
	</form>
	
</div>

<jsp:include page="/footer.jsp"/>
    