package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberAddServlet implements Servlet {

  MemberDao memberDao;

  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    Member member = new Member();

    out.println("번호? \n!{}!");
    out.flush();
    member.setNo(Integer.parseInt(in.nextLine()));
    
    out.println("이름? \n!{}!");
    out.flush();
    member.setName(in.nextLine());

    out.println("이메일? \n!{}!");
    out.flush();
    member.setEmail(in.nextLine());

    out.println("암호? \n!{}!");
    out.flush();
    member.setPassword(in.nextLine());

    out.println("사진? \n!{}!");
    out.flush();
    member.setPhoto(in.nextLine());

    out.println("전화? \n!{}!");
    out.flush();
    member.setTel(in.nextLine());

    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    try {
      memberDao.insert(member);
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("멤버 추가 저장 중 오류발생");
    }
  }
}
