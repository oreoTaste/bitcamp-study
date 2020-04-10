<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp" />

<div class='container'>
<h1>게시물입력</h1>

<form action='add' method='post'>
	내용:<br>
	<textarea name='title' rows='5' cols='60'></textarea>
	<br>
	
	<button>등록</button>
</form>

</div>

<jsp:include page="/footer.jsp" />