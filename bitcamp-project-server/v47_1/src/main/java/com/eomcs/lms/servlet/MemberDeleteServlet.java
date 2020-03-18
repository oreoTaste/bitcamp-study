package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.service.MemberService;
import com.eomcs.util.Component;
import com.eomcs.util.Prompt;
import com.eomcs.util.RequestMapping;

@Component
public class MemberDeleteServlet {

  MemberService memberService;

  public MemberDeleteServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/delete")
  public void service(Scanner in, PrintStream out) throws Exception {
    
    try {
      int no = Prompt.getInt(in, out, "번호?");

      if(memberService.delete(no)) {
        out.println("회원을 삭제했습니다.");
      } else {
        out.println("해당 번호의 회원이 없습니다");
      }

    } catch (Exception e) {
      out.println("멤버 정보 수신 중 오류발생!");
    }
  }
}
