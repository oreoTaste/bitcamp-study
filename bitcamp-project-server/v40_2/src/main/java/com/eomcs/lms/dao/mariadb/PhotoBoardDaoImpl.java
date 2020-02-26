package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.ConnectionFactory;

public class PhotoBoardDaoImpl implements PhotoBoardDao {
  ConnectionFactory conFactory;
  
  public PhotoBoardDaoImpl(ConnectionFactory conFactory) {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("INSERT INTO lms_photo(titl, lesson_id) values('" +
          photoBoard.getTitle() + "', +"
          + photoBoard.getLesson().getNo()+")", Statement.RETURN_GENERATED_KEYS);

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
    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select photo_id, titl, cdt, vw_cnt, lesson_id"
                + " from lms_photo"
                + " where lesson_id = " + lessonNo
                + " order by photo_id desc")) {

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
    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(

            "SELECT" +
                " p.photo_id," +
                " p.titl," +
                " p.cdt," +
                " p.vw_cnt," +
                " l.lesson_id," +
                " l.titl lesson_title" +
                " FROM lms_photo p inner join lms_lesson l" +
                " on p.lesson_id = l.lesson_id" +
                " WHERE photo_id = " + no)) {

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
    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "UPDATE lms_photo SET" +
              " titl = '" + photoBoard.getTitle() +
              "', cdt = '" + new Date(System.currentTimeMillis()) +
              "', vw_cnt = 0" +
              " WHERE photo_id = " + photoBoard.getNo());
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "DELETE FROM lms_photo WHERE photo_id = " + no);
      return result;
    }
  }

}
