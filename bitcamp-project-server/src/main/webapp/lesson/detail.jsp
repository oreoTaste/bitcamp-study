<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp"/>

<div class="container">
<h1>수업 상세정보(JSP + EL)</h1>
<form action='update' method='post'>

      번호? <input name='no' readonly type='text' value='${lesson.no}'><br>
      강의명: <input name='title' type='text' value='${lesson.title}'><br>
      내용: <br><textarea name='context' rows='5' cols='60'>${lesson.context}</textarea><br>
      기간 : <input name='startDate' type='date' value='${lesson.startDate}'> ~ 
      <input name='endDate' type='date' value='${lesson.endDate}'><br>
      총수업시간: <input name='totalHour' type='number' value='${lesson.totalHour}'><br>
      일수업시간: <input name='dailyHour' type='number' value='${lesson.dailyHour}'><br>

<p>
<button>변경</button>
<a href='delete?no=${lesson.no}'>삭제</a>
<a href='../photoboard/list?lessonNo=${lesson.no}'>사진게시판</a>
</p>
</form>
</div>
<jsp:include page="/footer.jsp"/>
    