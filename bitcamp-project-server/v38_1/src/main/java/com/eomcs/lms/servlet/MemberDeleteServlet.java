package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;

public class MemberDeleteServlet implements Servlet {

  MemberDao memberDao;

  public MemberDeleteServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    try {
      out.println("번호?\n!{}!");
      out.flush();
      int no = Integer.parseInt(in.nextLine());

      if(memberDao.delete(no) > 0) {
        System.out.println("회원을 삭제했습니다.");
      } else {
        System.out.println("해당 번호의 회원이 없습니다");
      }

    } catch (Exception e) {
      System.out.println("멤버 정보 수신 중 오류발생!");
    }
  }
}
