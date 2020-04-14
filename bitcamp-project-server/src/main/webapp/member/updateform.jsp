<%@page import="java.util.List"%>
<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp" />

<div class='container'>
	<h1>멤버 수정</h1>

	<%
	List<Member> member = (List<Member>) request.getAttribute("member");
	  if(member == null) {
  %>
	<p>해당 번호의 멤버가 없습니다.</p>
	<%
      } else {
  %>
        <table border='1'>
        <tr>
        <th>멤버번호</th>
        <th>성함</th>
        <th>이메일</th>
        <th>전화번호</th>
        <th>등록일</th>
        </tr>
  <%
        for (Member m : member) {
  %>

          <tr>
          <td><a href='detail?no=${m.getNo()}'>${m.getNo()}</a></td>
          <td><a href='detail?no=${m.getNo()}'>${m.getName()}</a></td>
          <td><a href='detail?no=${m.getNo()}'>${m.getEmail()}</a></td>
          <td><a href='detail?no=${m.getNo()}'>${m.getTel()}</a></td>
          <td><a href='detail?no=${m.getNo()}'>${m.getRegisteredDate()}</a></td>
          </tr>

  <%
  }
  %>
  </table>




</div>
  <%
      }
  %>
<jsp:include page="/footer.jsp" />