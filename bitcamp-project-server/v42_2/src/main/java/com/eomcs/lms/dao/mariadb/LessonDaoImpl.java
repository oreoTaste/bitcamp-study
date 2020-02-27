package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.sql.DataSource;

public class LessonDaoImpl implements LessonDao {
  DataSource dataSource;

  public LessonDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Lesson lesson) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO lms_lesson(sdt, edt, tot_hr, day_hr, titl, conts) "
                + "values(?, ?, ?, ?, ?, ?)")) {

      stmt.setDate(1, lesson.getStartDate());
      stmt.setDate(2, lesson.getEndDate());
      stmt.setInt(3, lesson.getTotalHour());
      stmt.setInt(4, lesson.getDailyHour());
      stmt.setString(5, lesson.getTitle());
      stmt.setString(6, lesson.getContext());

      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Lesson> findAll() throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select lesson_id, sdt, edt, tot_hr, day_hr, titl, conts from lms_lesson")){

      ArrayList<Lesson> list = new ArrayList<>();
      ResultSet rs = stmt.executeQuery();

      // ResultSet 도구를 사용하여 데이터를 하나씩 가져온다.
      while(rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lesson_id"));
        lesson.setStartDate(rs.getDate("sdt"));
        lesson.setEndDate(rs.getDate("edt"));
        lesson.setTotalHour(rs.getInt("tot_hr"));
        lesson.setDailyHour(rs.getInt("day_hr"));
        lesson.setTitle(rs.getString("titl"));
        lesson.setContext(rs.getString("conts"));

        list.add(lesson);
      }
      return list;
    }
  }

  @Override
  public Lesson findByNo(int no) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "SELECT lesson_id, sdt, edt, tot_hr, day_hr, titl, conts from lms_lesson"
                + " where lesson_id = ?")){
      stmt.setInt(1, no);
      ResultSet rs = stmt.executeQuery();

      if(rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lesson_id"));
        lesson.setStartDate(rs.getDate("sdt"));
        lesson.setEndDate(rs.getDate("edt"));
        lesson.setTotalHour(rs.getInt("tot_hr"));
        lesson.setDailyHour(rs.getInt("day_hr"));
        lesson.setTitle(rs.getString("titl"));
        lesson.setContext(rs.getString("conts"));
        return lesson;

      } else
        return null;

    }
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "UPDATE lms_lesson SET sdt = ?, edt = ?, tot_hr = ?, "
                + "day_hr = ?, titl = ?, conts = ? WHERE lesson_id = ?")) {

      stmt.setDate(1, lesson.getStartDate());
      stmt.setDate(2, lesson.getEndDate());
      stmt.setInt(3, lesson.getTotalHour());
      stmt.setInt(4, lesson.getDailyHour());
      stmt.setString(5, lesson.getTitle());
      stmt.setString(6, lesson.getContext());
      stmt.setInt(7, lesson.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "DELETE FROM lms_lesson WHERE lesson_id = ?")) {
      
      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }

}
