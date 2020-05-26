<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp</title>
</head>
<body>
<h1>/WEB-INF/jsp2/error1_post.jsp</h1>

<p>상태코드 : ${requestScope[("javax.servlet.error.status_code")]}</p>
<p>상세 오류 메시지: ${requestScope[("javax.servlet.error.message")]}</p>

</body>
</html>
<%
System.out.println("error1_post.jsp 실행");
%>