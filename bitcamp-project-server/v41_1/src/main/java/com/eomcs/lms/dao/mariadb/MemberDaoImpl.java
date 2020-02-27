package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.sql.DataSource;

public class MemberDaoImpl implements MemberDao {
  DataSource dataSource;
  
  public MemberDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Member board) throws Exception {
    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("INSERT INTO lms_member(name, email, pwd, tel, photo)"
          + " values('" + board.getName() + "','" + board.getEmail() + "','" +
          board.getPassword() + "','" +board.getTel() + "','" + board.getPhoto()+ "')");
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_id, name, email, pwd, cdt, tel, photo from lms_member")) {

      ArrayList<Member> list = new ArrayList<>();

      // ResultSet 도구를 사용하여 데이터를 하나씩 가져온다.
      while(rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setRegisteredDate(rs.getDate("cdt"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));

        list.add(member);
      }

      return list;
    }
  }

  @Override
  public Member findByNo(int no) throws Exception {
    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT member_id, name, email, pwd, cdt, tel, photo FROM lms_member "
                + "WHERE member_id = " + no)) {

      if(rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setRegisteredDate(rs.getDate("cdt"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));
        return member;

      } else
        return null;

    }
  }

  @Override
  public int update(Member member) throws Exception {
    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "UPDATE lms_member SET name = '" + member.getName() +
          "', email = '" + member.getEmail() +
          "', pwd = '" + member.getPassword() +
          "', cdt = '" + new Date(System.currentTimeMillis()) +
          "', tel = '" + member.getTel() +
          "', photo = '" + member.getPhoto() +
          "' WHERE member_id = " + member.getNo());
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "DELETE FROM lms_member WHERE member_id = " + no);
      return result;
    }
  }


  @Override
  public List<Member> findByKeyword(String keyword) throws Exception {
    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_id, name, email, pwd, cdt, tel, photo"
                + " from lms_member"
                + " where name like '%"
                + keyword
                + "%'"
                + " or email like '%"
                + keyword
                + "%'"
                + " or tel like '%"
                + keyword
                + "%'")
        ) {

      ArrayList<Member> list = new ArrayList<>();

      // ResultSet 도구를 사용하여 데이터를 하나씩 가져온다.
      while(rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setRegisteredDate(rs.getDate("cdt"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));

        list.add(member);
      }

      return list;
    }
  }

}
