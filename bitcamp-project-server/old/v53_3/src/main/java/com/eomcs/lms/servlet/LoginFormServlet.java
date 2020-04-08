package com.eomcs.lms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.util.RequestMapping;

@Component
public class LoginFormServlet {

  @RequestMapping("/auth/loginForm")
  public void service(Map<String, String> map, PrintWriter out) throws Exception {
    try {
      printHead(out);
      
      out.printf("<form action='/auth/login'>");
      out.printf("<div><table border='1'>");
      out.printf("<tr><th>이메일</th><td><input name='email'></td></tr>");
      out.printf("<tr><th>비밀번호</th><td><input type='password' name='password'></td></tr></div>");
      
      out.printf("<button>로그인</button>");

    } catch(Exception e) {
    }
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
