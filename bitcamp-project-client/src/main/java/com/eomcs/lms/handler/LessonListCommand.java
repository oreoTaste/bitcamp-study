package com.eomcs.lms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.eomcs.lms.dao.LessonDao;

public class LessonListCommand implements Command {
  LessonDao lessonDao;

  public LessonListCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute() {

    try {
      
      Class.forName("org.mariadb.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(
          "select lesson_id, sdt, edt, tot_hr, day_hr, titl, conts from lms_lesson");
      while(rs.next()) {
        System.out.printf("%d, %s     , %tF ~ %tF, %d\n",
            rs.getInt("lesson_id"),
            rs.getString("titl"),
            rs.getDate("sdt"),
            rs.getDate("edt"),
            rs.getInt("tot_hr"));
      }
      
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    /*
    List<Lesson> lesson;
    try {
      lesson = lessonDao.findAll();
      for (Lesson ls : lesson) {

      }
    } catch (Exception e) {
      System.out.println("수업 리스트 수신중 오류 발생!");
    }
    */
  }


}

