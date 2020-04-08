package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;
import com.eomcs.util.RequestMapping;

@Component
public class MemberAddServlet {

  MemberService memberService;

  public MemberAddServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/add")
  public void service(Map<String, String> map, PrintStream out) throws Exception {
    printHead(out);
    Member member = new Member();

    member.setName(map.get("name"));
    member.setEmail(map.get("email"));
    member.setPassword(map.get("password"));
    member.setPhoto(map.get("photo"));
    member.setTel(map.get("tel"));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    try {
      memberService.add(member);
      out.println("저장하였습니다.");

    } catch (Exception e) {
      out.println("멤버 추가 중복값이 있어 등록이 불가합니다.");
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
    out.println("<meta >");
    out.println("<title>멤버 추가</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>멤버 추가</h1>");
  }
}
