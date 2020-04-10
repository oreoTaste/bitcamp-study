package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.domain.Member;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
    String refreshUrl = (String) request.getAttribute("refreshUrl");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    if(refreshUrl!=null) {
      out.printf("  <meta http-equiv='refresh' content='1; url=%s'>", refreshUrl);
      System.out.println(refreshUrl);
    }
    out.println("<title>LMS 시스템</title>");
    out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh' crossorigin='anonymous'>");


    out.println("<style>");
    out.println("body {background-color: lightGray;}");
    out.println("div.container {background-color: white; border: 1px solid white; width: 1000px;}");
    out.println("</style>");

    out.println("</head>");
    out.println("<body>");

    out.println("<nav class='navbar navbar-expand-lg navbar-light bg-light' style='background-color: #e3f2fd;'>");
    out.println("<a class='navbar-brand' href='../index.html'>LMS 시스템</a>");
    out.println("<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarText' aria-controls='navbarText' aria-expanded='false' aria-label='Toggle navigation'>");
    out.println("<span class='navbar-toggler-icon'></span>");
    out.println("</button>");
    out.println("<div class='collapse navbar-collapse' id='navbarText'>");
    out.println("<ul class='navbar-nav mr-auto'>");
    out.println("<li class='nav-item active'>");
    out.println("<a class='nav-item nav-link' href='../board/list'>게시글 목록 보기</a>");
    out.println("</li>");
    out.println("<li class='nav-item active'>");
    out.println("<a class='nav-item nav-link' href='../lesson/list'>수업목록 보기</a>");
    out.println("</li>");
    out.println("<li class='nav-item active'>");
    out.println("<a class='nav-item nav-link' href='../member/list'>멤버목록 보기</a>");
    out.println("<li class='nav-item active'>");
    out.println("</ul>");

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if(loginUser!=null) {
      out.println("<a href='../auth/logout' class='btn btn-light'>로그아웃</a>");

      out.println("<span class='navbar-text'>");
      out.printf("%s님 반갑습니다", loginUser.getName());
      out.println("</span>");
    } else {
      out.println("<a href='../auth/login' class='btn btn-light'>로그인</a>");
    }
    out.println("</div>");
    out.println("</nav>");

    out.println("<div class='container'>");

  }
}
