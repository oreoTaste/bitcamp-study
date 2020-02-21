package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonUpdateServlet implements Servlet {

  LessonDao lessonDao;

  public LessonUpdateServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      
      out.println("번호? \n!{}!");
      out.flush();
      int no = Integer.parseInt(in.nextLine());
      
      Lesson oldLesson = lessonDao.findByNo(no);
      Lesson newLesson = new Lesson();

      newLesson.setNo(oldLesson.getNo());

      out.printf("수업명? (%s) \n!{}!", oldLesson.getTitle());
      out.println();
      out.flush();
      newLesson.setTitle(in.nextLine());
      
      out.printf("수업내용? (%s) \n!{}!", oldLesson.getContext());
      out.println();
      out.flush();
      newLesson.setContext(in.nextLine());
      
      out.printf("시작일? (%s) \n!{}!", oldLesson.getStartDate());
      out.println();
      out.flush();
      newLesson.setStartDate(Date.valueOf(in.nextLine()));
      
      out.printf("종료일? (%s) \n!{}!", oldLesson.getEndDate());
      out.println();
      out.flush();
      newLesson.setEndDate(Date.valueOf(in.nextLine()));
      
      out.printf("총수업시간? (%d)\n!{}!", oldLesson.getTotalHour());
      out.println();
      out.flush();
      newLesson.setTotalHour(Integer.parseInt(in.nextLine()));
      
      out.printf("일수업시간? (%d)\n!{}!", oldLesson.getDailyHour());
      out.println();
      out.flush();
      newLesson.setDailyHour(Integer.parseInt(in.nextLine()));
      

      if (newLesson.equals(oldLesson)) {
        System.out.println("수업 변경을 취소했습니다.");
        return;
      }
        lessonDao.update(newLesson);
        System.out.println("수업을 변경했습니다.");
        
    } catch (Exception e) {
      System.out.println("수업정보 업데이트 중 오류발생");
    }

  }
}
