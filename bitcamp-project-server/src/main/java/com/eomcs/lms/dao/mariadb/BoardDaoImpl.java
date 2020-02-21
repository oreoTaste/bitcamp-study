package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDaoImpl implements BoardDao {

  Connection con;

  public BoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Board board) throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    try(Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);

      return stmt.executeUpdate("INSERT INTO lms_board(conts) values('" +
          board.getTitle() + "')");
    }
  }

  @Override
  public List<Board> findAll() throws Exception {

    // JDBC Driver (MariaDB 프록시)를 로딩한다.
    Class.forName("org.mariadb.jdbc.Driver");

    try(// JDBC Driver를 이용하여 MariaDB에 접속한다.
        // MariaDB에 명령을 전달할 객체 준비
        Statement stmt = con.createStatement();

        // MariaDB에 lms_board 테이블에 있는 데이터를 가져올 도구 준비.
        // Structured Query Language (SQL)
        ResultSet rs = stmt.executeQuery(
            "select board_id, conts, cdt, vw_cnt from lms_board")) {

      ArrayList<Board> list = new ArrayList<>();

      // ResultSet 도구를 사용하여 데이터를 하나씩 가져온다.
      while(rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("conts"));
        board.setDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));

        list.add(board);
      }

      return list;
    }
  }

  @Override
  public Board findByNo(int no) throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");

    try(Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT board_id, conts, cdt, vw_cnt FROM lms_board "
                + "WHERE board_id = " + no)) {

      if(rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("conts"));
        board.setDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));
        return board;

      } else
        return null;

    }
  }

  @Override
  public int update(Board board) throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    try(Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "UPDATE lms_board SET conts = '" + board.getTitle() +
          "', cdt = '" + new Date(System.currentTimeMillis()) +
          "' WHERE board_id = " + board.getNo());
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");
    try(Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "DELETE FROM lms_board WHERE board_id = " + no);
      return result;
    }
  }

}
