package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

public class MemberListServlet implements Servlet {

  MemberService memberService;

  public MemberListServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
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