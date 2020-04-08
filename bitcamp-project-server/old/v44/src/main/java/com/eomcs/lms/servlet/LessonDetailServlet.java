package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.Prompt;

public class LessonDetailServlet implements Servlet {

  LessonService lessonService;

  public LessonDetailServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    try {
    int no = Prompt.getInt(in, out, "번호? ");
    
      Lesson lesson = lessonService.get(no);

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
