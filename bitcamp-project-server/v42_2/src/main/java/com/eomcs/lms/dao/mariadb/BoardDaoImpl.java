package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.sql.DataSource;

public class BoardDaoImpl implements BoardDao {

  DataSource dataSource;

  public BoardDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Board board) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO lms_board(conts) values(?)")) {

      stmt.setString(1, board.getTitle());
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select board_id, conts, cdt, vw_cnt from lms_board order by board_id desc")){

      ResultSet rs = stmt.executeQuery();
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
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "SELECT board_id, conts, cdt, vw_cnt FROM lms_board WHERE board_id = ?")){

      stmt.setInt(1, no);
      ResultSet rs = stmt.executeQuery();

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
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "UPDATE lms_board SET conts = ?, cdt = ? WHERE board_id = ?")) {

      stmt.setString(1, board.getTitle());
      stmt.setDate(2, new Date(System.currentTimeMillis()));
      stmt.setInt(3, board.getNo());
      
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "DELETE FROM lms_board WHERE board_id = ?")) {

      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }

}
