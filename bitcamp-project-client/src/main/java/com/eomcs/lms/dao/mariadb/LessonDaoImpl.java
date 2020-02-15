package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoImpl implements LessonDao {

  @Override
  public int insert(Lesson lesson) throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
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
    
    // JDBC Driver (MariaDB 프록시)를 로딩한다.
    Class.forName("org.mariadb.jdbc.Driver");

    try(// JDBC Driver를 이용하여 MariaDB에 접속한다.
        Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/studydb", "study", "1111");

        // MariaDB에 명령을 전달할 객체 준비
        Statement stmt = con.createStatement();

        // MariaDB에 lms_board 테이블에 있는 데이터를 가져올 도구 준비.
        // Structured Query Language (SQL)
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

    Class.forName("org.mariadb.jdbc.Driver");

    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
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

    Class.forName("org.mariadb.jdbc.Driver");
    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
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
    Class.forName("org.mariadb.jdbc.Driver");
    try(Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "DELETE FROM lms_lesson WHERE lesson_id = " + no);
      return result;
    }
  }

}
