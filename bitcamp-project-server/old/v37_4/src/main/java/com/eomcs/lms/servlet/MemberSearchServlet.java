package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberSearchServlet implements Servlet {

  MemberDao memberDao;

  public MemberSearchServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("검색어? \n!{}!");
    out.flush();
    String keyword = in.nextLine();

    List<Member> members = memberDao.findByKeyword(keyword);
    for (Member m : members) {
      out.printf("%d, \t\t%s, \t\t\t%-10s, \t\t%s, \t%s\n",
          m.getNo(),
          m.getName(),
          m.getEmail(),
          m.getTel(),
          m.getRegisteredDate());
    }

  }
}