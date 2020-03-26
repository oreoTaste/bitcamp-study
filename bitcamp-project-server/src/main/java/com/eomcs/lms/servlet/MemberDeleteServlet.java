package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.service.MemberService;
import com.eomcs.util.RequestMapping;

@Component
public class MemberDeleteServlet {

  MemberService memberService;

  public MemberDeleteServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/delete")
  public void service(Map<String, String> map, PrintStream out) throws Exception {
    
    try {
      printHead(out);
      int no = Integer.parseInt(map.get("no"));

      if(memberService.delete(no)) {
        out.println("회원을 삭제했습니다.");
      } else {
        out.println("해당 번호의 회원이 없습니다");
      }

    } catch (Exception e) {
      
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
    out.println("<meta http-equiv='refresh' content=\"2; url='/member/list'\">");
    out.println("<title> 멤버 삭제 </title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>멤버 삭제</h1>");
  }
  
}
