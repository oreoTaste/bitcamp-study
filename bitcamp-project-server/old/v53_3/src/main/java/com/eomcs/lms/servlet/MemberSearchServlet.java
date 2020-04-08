package com.eomcs.lms.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;
import com.eomcs.util.RequestMapping;

@Component
public class MemberSearchServlet {

  MemberService memberService;

  public MemberSearchServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/search")
  public void service(Map<String, String> map, PrintWriter out) throws Exception {

    printHead(out);
    String keyword = map.get("keyword");
    
    List<Member> members = memberService.search(keyword);
    if(!members.isEmpty()) {
      
      out.printf("<table border='1'>");
      out.printf("<tr>");
      out.printf("<th>멤버번호</th>");
      out.printf("<th>성함</th>");
      out.printf("<th>이메일</th>");
      out.printf("<th>전화번호</th>");
      out.printf("<th>등록일</th>");
      out.printf("</tr>");
      
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
    out.println("<title> 멤버 목록 </title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>멤버 목록</h1>");
  }

}