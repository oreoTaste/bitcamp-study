package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.ConnectionFactory;

public class BoardDaoImpl implements BoardDao {

  ConnectionFactory conFactory;
  
  public BoardDaoImpl(ConnectionFactory conFactory) {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(Board board) throws Exception {
    Connection c1 = conFactory.getConnection();
    Connection c2 = conFactory.getConnection();
    System.out.println(c1 == c2);
    
    Connection c3 = conFactory.getConnection();
    System.out.println(c1 == c3);
    
    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {
      
      return stmt.executeUpdate("INSERT INTO lms_board(conts) values('" +
          board.getTitle() + "')");
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select board_id, conts, cdt, vw_cnt from lms_board order by board_id desc")) {

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
    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement();
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
    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "UPDATE lms_board SET conts = '" + board.getTitle() +
          "', cdt = '" + new Date(System.currentTimeMillis()) +
          "' WHERE board_id = " + board.getNo());
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "DELETE FROM lms_board WHERE board_id = " + no);
      return result;
    }
  }

}
