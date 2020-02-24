package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDetailServlet implements Servlet {

  LessonDao lessonDao;

  public LessonDetailServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    try {
    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());
    
      Lesson lesson = lessonDao.findByNo(no);

      out.printf("번호? %d\n", lesson.getNo());
      out.printf("수업명: %s\n", lesson.getTitle());
      out.printf("수업내용: %s\n", lesson.getContext());
      out.printf("기간 : %tF ~ %tF\n", lesson.getStartDate(), lesson.getEndDate());
      out.printf("총수업시간: %d\n", lesson.getTotalHour());
      out.printf("일수업시간: %d\n", lesson.getDailyHour());
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("수업정보 세부사항 수신 중 오류발생");
    }
  }
}
