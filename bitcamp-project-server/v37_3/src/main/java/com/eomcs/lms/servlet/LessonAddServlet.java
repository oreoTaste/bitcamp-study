package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonAddServlet implements Servlet {

  LessonDao lessonDao;

  public LessonAddServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    try {

      Lesson lesson = new Lesson();
      out.println("번호? \n!{}!");
      out.flush();
      lesson.setNo(Integer.parseInt(in.nextLine()));

      out.println("수업명? \n!{}!");
      out.flush();
      lesson.setTitle(in.nextLine());

      out.println("수업내용? \n!{}!");
      out.flush();
      lesson.setContext(in.nextLine());

      out.println("시작일? (형식 : 2019-01-01) \n!{}!");
      out.flush();
      lesson.setStartDate(Date.valueOf(in.nextLine()));
      
      out.println("종료일? (형식 : 2019-01-01) \n!{}!");
      out.flush();
      lesson.setEndDate(Date.valueOf(in.nextLine()));

      out.println("총수업시간? (형식: 1000) \n!{}!");
      out.flush();
      lesson.setTotalHour(Integer.parseInt(in.nextLine()));

      out.println("일수업시간? (형식: 8) \n!{}!");
      out.flush();
      lesson.setDailyHour(Integer.parseInt(in.nextLine()));

      if (lessonDao.insert(lesson) > 0) { // 삭제했다면
        out.println("새 글을 등록했습니다.");

      } else {
        out.println("수업정보 등록에 실패했습니다.");
      }

    } catch (Exception e) {
      System.out.println("수업정도 추가중 오류발생");
    }
  }
}
