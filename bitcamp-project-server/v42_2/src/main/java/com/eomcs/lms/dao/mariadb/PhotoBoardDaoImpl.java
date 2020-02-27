package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.sql.DataSource;

public class PhotoBoardDaoImpl implements PhotoBoardDao {
  DataSource dataSource;

  public PhotoBoardDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO lms_photo(titl, lesson_id) VALUES(?, ?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, photoBoard.getTitle());
      stmt.setInt(2, photoBoard.getLesson().getNo());
      int result = stmt.executeUpdate();

      // Auto-increment PK값을 꺼내는 작업
      try(ResultSet generatedKeySet = stmt.getGeneratedKeys()) {
        // PK 컬럼의 값을 꺼낸다
        generatedKeySet.next();
        // PK 컬럼의 값을 가져와서 담는다.
        photoBoard.setNo(generatedKeySet.getInt(1));
      }

      return result;
    }
  }

  @Override
  public List<PhotoBoard> findAllByLessonNo(int lessonNo) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "SELECT photo_id, titl, cdt, vw_cnt, lesson_id FROM lms_photo"
                + " WHERE lesson_id = ? ORDER BY photo_id DESC")){
      
      stmt.setInt(1, lessonNo);
      
      ResultSet rs = stmt.executeQuery();

      ArrayList<PhotoBoard> list = new ArrayList<>();
      while(rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setCreatedDate(rs.getDate("cdt"));
        photoBoard.setViewCount(rs.getInt("vw_cnt"));

        list.add(photoBoard);
      }

      return list;
    }
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "SELECT" +
                " p.photo_id," +
                " p.titl," +
                " p.cdt," +
                " p.vw_cnt," +
                " l.lesson_id," +
                " l.titl lesson_title" +
                " FROM lms_photo p inner join lms_lesson l" +
                " on p.lesson_id = l.lesson_id" +
            " WHERE photo_id = ?")){

      stmt.setInt(1, no);
      ResultSet rs = stmt.executeQuery();

      if(rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setCreatedDate(rs.getDate("cdt"));
        photoBoard.setViewCount(rs.getInt("vw_cnt"));

        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lesson_id"));
        lesson.setTitle(rs.getString("lesson_title"));
        photoBoard.setLesson(lesson);

        return photoBoard;

      } else
        return null;

    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "UPDATE lms_photo SET"
            + " titl = ?, cdt = ?, vw_cnt = 0 WHERE photo_id = ?")) {
      
      stmt.setString(1, photoBoard.getTitle());
      stmt.setDate(2, new Date(System.currentTimeMillis()));
      stmt.setInt(3, photoBoard.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "DELETE FROM lms_photo WHERE photo_id = ?")) {

      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }

}
