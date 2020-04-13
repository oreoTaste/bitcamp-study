<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp" />

<div class='container'>
	<h1>게시글 수정</h1>

	<%Member member = (Member) request.getAttribute("member");
	  if(member == null) {
  %>
	<p>해당 번호의 멤버가 없습니다.</p>
	<%
      } else {
  %>

  <form action='update' method='post' enctype='multipart/form-data'>
  <table border="1">
		<tr>
		  <td>번호 : </td>
		  <td><input  readonly name='no' type='text' value='${member.no}'></td>
		</tr>
    <tr>
			<td>성함:</td>
      <td><input  name='name' type='text' value='${member.name}'></td>
    </tr>
    <tr>
			<td>이메일: </td>
      <td><input  name='email' type='text' value='${member.email}'></td>
    </tr>
    <tr>
			<td>비밀번호: </td>
      <td><input  name='password' type='text' value='${member.password}'></td>
    </tr>
    <tr>
			<td>사진: </td>
      <td><input  name='photo' type='file' value='${member.photo}'></td>
    </tr>
    <tr>
			<td>전화번호: </td>
      <td><input  name='tel' type='text' value='${member.tel}'></td>
    </tr>
    <tr>
			<td>등록일: </td>
      <td><input  readonly name='registeredDate' type='text' value='${member.registeredDate}'></td>
    </tr>
	</table>
	<button>수정하기</button>
	</form>
</div>
  <%
      }
  %>
<jsp:include page="/footer.jsp" />