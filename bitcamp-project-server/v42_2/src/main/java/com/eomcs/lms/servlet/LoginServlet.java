package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Prompt;

public class LoginServlet implements Servlet {

  MemberDao memberDao;

  public LoginServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    try {

      String email = Prompt.getString(in, out, "이메일?");
      String password = Prompt.getString(in, out, "암호?");
      Member member = memberDao.findByEmailAndPassword(email, password);

      if(member == null) {
        out.println("사용자 정보가 유효하지 않습니다.");
      } else {
        out.println(String.format("'%s'님 반갑습니다.", member.getName()));
      }
    } catch(Exception e) {
      out.println("사용자 정보가 유효하지 않습니다.");
    }
  }
}
