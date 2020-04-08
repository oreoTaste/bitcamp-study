package com.eomcs.lms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.util.RequestMapping;

@Component
public class MemberAddFormServlet {

  @RequestMapping("/member/addForm")
  public void service(Map<String, String> map, PrintWriter out) throws Exception {
    printHead(out);

    try {
      
      out.printf("<form action='/member/add'>");
      out.printf("성함: <input name='name' type='text'><br>");
      out.printf("이메일: <input name='email' type='text'><br>");
      out.printf("비밀번호: <input name='password' type='text'><br>");
      out.printf("사진: <input name='photo' type='text'><br>");
      out.printf("전화번호: <input name='tel' type='text'><br>");
      out.printf("<button>저장하기</button>");
      
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("멤버 추가 저장 중 오류발생");
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
    out.println("<title>멤버 추가</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>멤버 추가</h1>");
  }
  
}
