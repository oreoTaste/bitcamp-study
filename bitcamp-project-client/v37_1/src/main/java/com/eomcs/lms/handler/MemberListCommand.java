package com.eomcs.lms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.eomcs.lms.dao.MemberDao;

public class MemberListCommand implements Command {
  MemberDao memberDao;

  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    try {

      Class.forName("org.mariadb.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(
          "select member_id, name, email, pwd, cdt, tel, photo from lms_member");

      while(rs.next()) {

        System.out.printf("%d, %s, %s, %s, %tF\n",
            rs.getInt("member_id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("tel"),
            rs.getDate("cdt"));
      }
    } catch (Exception e) {
      System.out.println("멤버 리스트 수신중 오류발생");
    }


      /*
      List<Member> members = memberDao.findAll();
      for (Member m : members) {
        System.out.printf("%d, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(), m.getTel(),
            m.getRegisteredDate());
      }
    } catch (Exception e) {
      System.out.println("멤버 리스트 수신중 오류발생");
    }
       */
  }
}
