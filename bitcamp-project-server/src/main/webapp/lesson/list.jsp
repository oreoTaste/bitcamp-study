<%@page import="com.eomcs.lms.domain.Lesson"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp"/>

<div class='container'>

    <h1>수업목록</h1>

    <form action='search'>
    <input type='text' size='80' name='keyword' value='수업명을 입력해주세요'/>
    </form>
    <button>수업목록</button>
    <a href='add' method='get'>신규수업</a>
    <table border='1'>
    <tr>
    <th>레슨번호</th>
    <th>수업명</th>
    <th>수업내용</th>
    <th>시작일</th>
    <th>종료일</th>
    <th>총시간</th>
    <th>일시간</th>
    </tr>

    <%
     for (Lesson ls : lesson) {
     %>
        <tr>
        <td><a href='detail?no=${ls.getNo()}'>${ls.getNo()}</a></td>
        <td><a href='detail?no=${ls.getNo()}'>${ls.getTitle()}</a></td>
        <td><a href='detail?no=${ls.getNo()}'>${ls.getContext()}</a></td>
        <td><a href='detail?no=${ls.getNo()}'>${ls.getStartDate()}</a></td>
        <td><a href='detail?no=${ls.getNo()}'>${ls.getEndDate()}</a></td>
        <td><a href='detail?no=${ls.getNo()}'>${ls.getTotalHour()}</a></td>
        <td><a href='detail?no=${ls.getNo()}'>${ls.getDailyHour()}</a></td>
        </tr>
      </table>
</div>
<jsp:include page="/footer.jsp"/>
    