package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Prompt;

public class MemberUpdateServlet implements Servlet {

  MemberDao memberDao;

  public MemberUpdateServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    try {
      
      int no = Prompt.getInt(in, out, "번호? ");
      
      Member oldMember = memberDao.findByNo(no);
      Member newMember = new Member();

      newMember.setNo(oldMember.getNo());
      newMember.setRegisteredDate(new Date(System.currentTimeMillis()));
      newMember.setName(
          Prompt.getString(in, out, String.format("이름(%s)? ", oldMember.getName())));
      newMember.setEmail(
          Prompt.getString(in, out, String.format("이메일(%s)? ", oldMember.getEmail())));
      newMember.setPassword(
          Prompt.getString(in, out, String.format("암호(%s)? ", oldMember.getPassword())));
      newMember.setPhoto(
          Prompt.getString(in, out, String.format("사진(%s)? ", oldMember.getPhoto())));
      newMember.setTel(
          Prompt.getString(in, out, String.format("전화(%s)? ", oldMember.getTel())));
      
      if (oldMember.equals(newMember)) {
        out.println("회원 변경을 취소하였습니다.");
        return;
      }
      memberDao.update(newMember);

      out.println("회원을 변경했습니다.");

    } catch (Exception e) {
      out.println("멤버 정보 수정중 오류발생");
      e.printStackTrace();
    }
    
  }
}
