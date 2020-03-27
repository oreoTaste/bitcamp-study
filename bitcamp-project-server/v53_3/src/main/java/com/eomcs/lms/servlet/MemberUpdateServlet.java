package com.eomcs.lms.servlet;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;
import com.eomcs.util.RequestMapping;

@Component
public class MemberUpdateServlet {

  MemberService memberService;

  public MemberUpdateServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/update")
  public void service(Map<String, String> map, PrintWriter out) throws Exception {
    int no = Integer.parseInt(map.get("no"));
    
    try {
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.printf("<meta http-equiv='refresh' content=\"2; url='/member/detail?no=%d'\">", no);
      out.println("<title>멤버 정보 수정</title>");
      out.println("</head>");

      out.println("<body>");
      out.println("<h1>멤버 정보 수정</h1>");
    } catch(Exception e) {
      
    }
    
    try {
      
      String name = map.get("name");
      String email = map.get("email");
      String password =map.get("password");
      String photo = map.get("photo");
      String tel  =map.get("tel");
      
      Member newMember = new Member();

      newMember.setNo(no);
      newMember.setRegisteredDate(new Date(System.currentTimeMillis()));
      newMember.setName(name);
      newMember.setEmail(email);
      newMember.setPassword(password);
      newMember.setPhoto(photo);
      newMember.setTel(tel);
      
      memberService.update(newMember);

      out.println("회원을 변경했습니다.");

    } catch (Exception e) {
      out.println("멤버 정보 수정중 오류발생");
    }
    
    printTail(out);
    
  }
  
  private void printTail(PrintWriter out) {
    out.println("</body>");
    out.println("</html>");
  }

  
}
