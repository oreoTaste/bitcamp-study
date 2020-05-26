<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='container'>
	<h1>수업 정보 검색</h1>
	입력값 : ${keyword}
	<hr>
	
검색결과 :
	<table border='1'>
		<tr>
      <td>번호</td><td>이름</td><td>이메일</td><td>암호</td><td>사진</td><td>전화</td><td>등록일</td>
    </tr>
	<c:forEach items="${list}" var="item">
    <tr>
      <td>${item.no}</td>
      <td>${item.name}</td>
      <td>${item.email}</td>
      <td>${item.password}</td>
      <td>${item.photo}</td>
      <td>${item.tel}</td>
      <td>${item.registeredDate}</td>
    </tr>
	</c:forEach>
	</table>
</div>
