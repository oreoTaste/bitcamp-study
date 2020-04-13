<%@page import="com.eomcs.lms.domain.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp" />
<div class='container'>

	<h1>게시글 세부정보</h1>
	
	번호: ${board.no}<br> 
	제목: ${board.title}<br>
	등록일: ${board.date}<br> 
	조회수: ${board.viewCount}<br>
	
	<div>
		<a href='delete?no=${board.getNo()}'>삭제</a> ..
    <a href='update?no=${board.getNo()}'>변경</a> ..
    <a href='list'>게시글 목록으로 돌아가기</a>
	</div>
</div>
<jsp:include page="/footer.jsp" />