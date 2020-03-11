package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO lms_member(name, email, pwd, tel, photo)"
            + " VALUES( ?, ?, password(?), ?, ?)")) {

      stmt.setString(1, board.getName());
      stmt.setString(2, board.getEmail());
      stmt.setString(3, board.getPassword());
      stmt.setString(4, board.getTel());
      stmt.setString(5, board.getPhoto());
      
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "SELECT member_id, name, email, pwd, cdt, tel, photo FROM lms_member")
        ) {

      ResultSet rs = stmt.executeQuery();
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
        PreparedStatement stmt = con.prepareStatement(
            "SELECT member_id, name, email, pwd, cdt, tel, photo FROM lms_member"
                + " WHERE member_id = ?");
        ){

      stmt.setInt(1, no);

      ResultSet rs = stmt.executeQuery();

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
        PreparedStatement stmt = con.prepareStatement(
            "UPDATE lms_member SET name = ?, email = ?, pwd = password(?), "
            + "cdt = ?, tel = ?, photo = ? where member_id = ?")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setDate(4, new Date(System.currentTimeMillis()));
      stmt.setString(5, member.getTel());
      stmt.setString(6, member.getPhoto());
      stmt.setInt(7, member.getNo());
      
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "DELETE FROM lms_member WHERE member_id = ?")) {

      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }


  @Override
  public List<Member> findByKeyword(String keyword) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "SELECT member_id, name, email, pwd, cdt, tel, photo FROM lms_member "
                + "WHERE name like ? or email like ? or tel like ?");
        ) {
      String value = "%" + keyword + "%";
      stmt.setString(1, value);
      stmt.setString(2, value);
      stmt.setString(3, value);
      ResultSet rs = stmt.executeQuery();

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
  public Member findByEmailAndPassword(String email, String password) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "SELECT * from lms_member WHERE email = ? and pwd = password( ? )")) {

      stmt.setString(1, email);
      stmt.setString(2, password);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));
        return member;

      } else
        return null;
    }
  }

}
