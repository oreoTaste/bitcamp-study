<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp" />
<jsp:useBean id="board" class="com.eomcs.lms.domain.Board"
	scope="request" />

<div class='container'>
	<h1>게시글 세부정보</h1>
	
	번호: <%=board.getNo()%><br> 
	제목: <%=board.getTitle()%><br>
	등록일: <%=board.getDate()%><br> 
	조회수: <%=board.getViewCount()%><br>
	
	<div>
		<a href='delete?no=<%=board.getNo()%>'>삭제</a> ..
    <a href='update?no=<%=board.getNo()%>'>변경</a> ..
    <a href='list'>게시글 목록으로 돌아가기</a>
	</div>
</div>
<jsp:include page="/footer.jsp" />