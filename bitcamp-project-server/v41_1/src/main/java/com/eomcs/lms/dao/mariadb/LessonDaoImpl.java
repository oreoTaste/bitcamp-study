package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
        Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("INSERT INTO lms_lesson(sdt, edt, tot_hr, day_hr, titl, conts) "
          + "values('" + lesson.getStartDate() + "','"
          + lesson.getEndDate() + "','"
          + lesson.getTotalHour() + "','"
          + lesson.getDailyHour() + "','"
          + lesson.getTitle() + "','"
          + lesson.getContext() + "')");
    }
  }

  @Override
  public List<Lesson> findAll() throws Exception {
    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select lesson_id, sdt, edt, tot_hr, day_hr, titl, conts from lms_lesson")) {

      ArrayList<Lesson> list = new ArrayList<>();

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
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT lesson_id, sdt, edt, tot_hr, day_hr, titl, conts from lms_lesson"
                + " WHERE lesson_id = " + no)) {

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
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("UPDATE lms_lesson SET sdt ='"
          + lesson.getStartDate() + "',"
          + " edt = '" + lesson.getEndDate() + "',"
          + " tot_hr = '" + lesson.getTotalHour() + "',"
          + " day_hr = '" + lesson.getDailyHour() + "',"
          + " titl = '" + lesson.getTitle() + "',"
          + " conts = '" + lesson.getContext() + "' WHERE lesson_id = " + lesson.getNo());
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "DELETE FROM lms_lesson WHERE lesson_id = " + no);
      return result;
    }
  }

}
