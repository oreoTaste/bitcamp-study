package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;
import com.eomcs.util.RequestMapping;

@Component
public class MemberListServlet {

  MemberService memberService;

  public MemberListServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/list")
  public void service(Map<String, String> map, PrintStream out) throws Exception {

    try {
      printHead(out);

      out.println("<div><button onclick=\"location.href='/auth/loginForm'\">로그인</button'></div>");
      out.println("<div><form action='/member/search'>");
      out.println("<input name='keyword' type=text size='70' value='검색할 멤버'>");
      out.println("<button>검색</button></div>");
      
      out.println("<a href='/member/addForm'>멤버 추가</a><br>");

      List<Member> members = memberService.list();

      if(!members.isEmpty()) {
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>멤버번호</th>");
        out.println("<th>성함</th>");
        out.println("<th>이메일</th>");
        out.println("<th>전화번호</th>");
        out.println("<th>등록일</th>");
        out.println("</tr>");
        for (Member m : members) {

          out.printf("<tr>");
          out.printf("<td><a href='/member/detail?no=%d'>%d</a></td>", m.getNo(), m.getNo());
          out.printf("<td><a href='/member/detail?no=%d'>%s</a></td>", m.getNo(), m.getName());
          out.printf("<td><a href='/member/detail?no=%d'>%s</a></td>", m.getNo(), m.getEmail());
          out.printf("<td><a href='/member/detail?no=%d'>%s</a></td>", m.getNo(), m.getTel());
          out.printf("<td><a href='/member/detail?no=%d'>%s</a></td>", m.getNo(), m.getRegisteredDate());
          out.printf("</tr>");
        }
      }
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
    out.println("<title> 멤버 목록 </title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>멤버 목록</h1>");
  }

}