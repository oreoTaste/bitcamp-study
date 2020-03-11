package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberUpdateServlet implements Servlet {

  MemberDao memberDao;

  public MemberUpdateServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    try {
      out.println("번호? \n!{}!");
      out.flush();
      int no = Integer.parseInt(in.nextLine());
      
      Member oldMember = memberDao.findByNo(no);
      Member newMember = new Member();

      newMember.setNo(oldMember.getNo());
      newMember.setRegisteredDate(new Date(System.currentTimeMillis()));
      
      out.printf("이름(%s)? \n!{}!", oldMember.getName());
      out.println();
      out.flush();
      newMember.setName(in.nextLine());
      
      out.printf("이메일(%s)? \n!{}!", oldMember.getEmail());
      out.println();
      out.flush();
      newMember.setEmail(in.nextLine());
      
      out.printf("암호(%s)? \n!{}!", oldMember.getPassword());
      out.println();
      out.flush();
      newMember.setPassword(in.nextLine());
      
      out.printf("사진(%s)? \n!{}!", oldMember.getPhoto());
      out.println();
      out.flush();
      newMember.setPhoto(in.nextLine());

      out.printf("전화(%s)? \n!{}!", oldMember.getTel());
      out.println();
      out.flush();
      newMember.setTel(in.nextLine());
      
      if (oldMember.equals(newMember)) {
        System.out.println("회원 변경을 취소하였습니다.");
        return;
      }
      memberDao.update(newMember);

      System.out.println("회원을 변경했습니다.");

    } catch (Exception e) {
      System.out.println("멤버 정보 수정중 오류발생");
    }
    
  }
}
