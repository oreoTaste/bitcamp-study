package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberDetailServlet implements Servlet {

  MemberDao memberDao;

  public MemberDetailServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    try {
      out.println("번호? \n!{}!");
      out.flush();
      int no = Integer.parseInt(in.nextLine());
      
      Member member = memberDao.findByNo(no);

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
