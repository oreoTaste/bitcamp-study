<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header.jsp" />

<div class='container'>

<h1>멤버 세부정보</h1>

<c:if test="${not empty member}">

	<form action='update' method='post' enctype='multipart/form-data'>
	<img src='${pageContext.servletContext.contextPath}/upload/member/${member.photo}' height='80'><br>
	<table border='1' style='width: 70%'>
		<tr>
			<td>번호</td>
			<td><input style='width: 90%' readonly name='no' value='${member.no}'></td>
    </tr>
    <tr>
    	<td>이름</td>
    	<td><input style='width: 90%' name='name' value='${member.name}'></td>
    </tr>
    <tr>
			<td>이메일</td>
			<td><input style='width: 90%' name='email' value='${member.email}'></td>
    </tr>
    <tr>
			<td>암호</td>
			<td><input style='width: 90%' name='password' value='${member.password}'></td>
    </tr>
    <tr>
			<td>사진</td>
			<td><input style='width: 90%' name='photo' value='${member.photo}'></td>
    </tr>
    <tr>
			<td>전화</td>
			<td><input style='width: 90%' name='tel' value='${member.tel}'></td>
    </tr>
    <tr>
			<td>등록일</td>
			<td><input readonly style='width: 90%' type='date' name='registeredDate' value='${member.registeredDate}'></td>
		</tr>
			
	</table>

<p><button>변경</button>
<a href='delete?no=${member.no}'>삭제</a></p>
</form>
</c:if>

<c:if test="${empty member}">
<p>해당 회원이 없습니다.</p>
</c:if>


</div>
<jsp:include page="/footer.jsp" />