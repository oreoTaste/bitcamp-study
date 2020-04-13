<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp" />
<jsp:useBean id="member" class="com.eomcs.lms.domain.Member"
	scope="request" />

<div class='container'>
	<h1>멤버 세부정보</h1>
	<form action='update' method='post' enctype='multipart/form-data'>
		<img src='../upload/member/${member.getPhoto()}' height='40'><br>
	</form>
	<table border='1'>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>이메일</th>
			<th>암호</th>
			<th>사진</th>
			<th>전화</th>
			<th>등록일</th>
		</tr>
		<tr>
			<td>${member.getNo()}</td>
			<td>${member.getName()}</td>
			<td>${member.getEmail()}</td>
			<td>${member.getPassword()}</td>
			<td>${member.getPhoto()}</td>
			<td>${member.getTel()}</td>
			<td>${member.getRegisteredDate()}</td>
		</tr>
	</table>

	<br>
	<table border='1'>
	<tr>
	<form action="delete" method="post">
	<button name='no' value='${member.getNo()}'>삭제</button>
		..
		</form>
	<button onclick="location.href='update?no=${member.getNo()}'">수정</button>
	..
	<button onclick="location.href='list'">수업정보로 돌아가기</button>
  </tr>
	</table>
</div>
<jsp:include page="/footer.jsp" />