package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberListServlet implements Servlet {

  MemberDao memberDao;

  public MemberListServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {

      List<Member> members = memberDao.findAll();
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