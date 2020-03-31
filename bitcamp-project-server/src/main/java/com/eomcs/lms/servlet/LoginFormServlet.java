package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/auth/loginForm")
public class LoginFormServlet extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    printHead(out);

    out.printf("<form action='login'>");
    out.printf("<div><table border='1'>");
    out.printf("<tr><th>이메일</th><td><input name='email'></td></tr>");
    out.printf("<tr><th>비밀번호</th><td><input type='password' name='password'></td></tr></div>");

    out.printf("<button>로그인</button>");

    printTail(out);
  }

  private void printTail(PrintWriter out) {
    out.println("</body>");
    out.println("</html>");
  }

  private void printHead(PrintWriter out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>멤버 정보 수정</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>멤버 정보 수정</h1>");
  }

}
