package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
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
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      List<Member> members = memberService.list();
          
      for (Member m : members) {
        out.printf("%d, \t\t%s, \t\t\t%-10s, \t\t%s, \t%s\n",
            m.getNo(),
            m.getName(),
            m.getEmail(),
            m.getTel(),
            m.getRegisteredDate());
      }
    } catch(Exception e) {

    }

  }
}