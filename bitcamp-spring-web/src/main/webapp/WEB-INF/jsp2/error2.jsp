<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp</title>
</head>
<body>
<h1>/WEB-INF/jsp2/error2.jsp</h1>

<p>상태코드 : ${status}</p>
<p>상세 오류 메시지: ${reason}</p>
<p>상세 오류 메시지: ${msg}</p>

</body>
</html>
<%
System.out.println("error2.jsp 실행");
%>