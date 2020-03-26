package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.util.RequestMapping;

@Component
public class LoginFormServlet {

  @RequestMapping("/auth/loginForm")
  public void service(Map<String, String> map, PrintStream out) throws Exception {
    try {
      printHead(out);
      
      out.printf("<form action='/auth/login'>");
      out.printf("<table border='1'>");
      out.printf("<tr><th>이메일</th><th>비밀번호</th></tr>");
      out.printf("<tr><td><input name='email'></td><td><input name='password'></td></tr>");
      out.printf("<button>로그인</button>");

    } catch(Exception e) {
    }
    printTail(out);
  }
  
  private void printTail(PrintStream out) {
    out.println("</body>");
    out.println("</html>");
  }

  private void printHead(PrintStream out) {
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
