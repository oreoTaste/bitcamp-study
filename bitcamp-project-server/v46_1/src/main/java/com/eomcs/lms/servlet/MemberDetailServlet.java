package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;
import com.eomcs.util.Component;
import com.eomcs.util.Prompt;

@Component("/member/detail")
public class MemberDetailServlet implements Servlet {

  MemberService memberService;

  public MemberDetailServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    try {
      int no = Prompt.getInt(in, out, "번호? ");
      
      Member member = memberService.get(no);

      out.printf("번호: %d\n", member.getNo());
      out.printf("이름: %s\n", member.getName());
      out.printf("이메일: %s\n", member.getEmail());
      out.printf("암호: %s\n", member.getPassword());
      out.printf("사진: %s\n", member.getPhoto());
      out.printf("전화: %s\n", member.getTel());
      out.printf("등록일: %1$tF %1$tH:%1$tM:%1$tS\n", member.getRegisteredDate());

    } catch (Exception e) {
      System.out.println("멤버 정보 수신중 오류발생!");
    }
    
  }
}
