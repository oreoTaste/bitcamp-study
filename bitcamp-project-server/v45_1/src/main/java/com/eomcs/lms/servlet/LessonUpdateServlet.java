package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.Prompt;

public class LessonUpdateServlet implements Servlet {

  LessonService lessonService;

  public LessonUpdateServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      
      int no = Prompt.getInt(in, out, "번호? ");
      
      Lesson oldLesson = lessonService.get(no);
      Lesson newLesson = new Lesson();

      newLesson.setNo(oldLesson.getNo());
      newLesson.setTitle(
          Prompt.getString(in, out, String.format("수업명? (%s) ", oldLesson.getTitle())));
      newLesson.setContext(
          Prompt.getString(in, out, String.format("수업내용? (%s) ", oldLesson.getContext())));
      newLesson.setStartDate(
          Prompt.getDate(in, out, String.format("시작일? (%s) ", oldLesson.getStartDate())));
      newLesson.setEndDate(
          Prompt.getDate(in, out, String.format("종료일? (%s) ", oldLesson.getEndDate())));
      newLesson.setTotalHour(
          Prompt.getInt(in, out, String.format("총수업시간? (%d)", oldLesson.getTotalHour())));
      newLesson.setDailyHour(
          Prompt.getInt(in, out, String.format("일수업시간? (%d)", oldLesson.getDailyHour())));
      

      if (newLesson.equals(oldLesson)) {
        out.println("수업 변경을 취소했습니다.");
        return;
      }
      lessonService.update(newLesson);
        out.println("수업을 변경했습니다.");
        
    } catch (Exception e) {
      out.println("수업정보 업데이트 중 오류발생");
    }

  }
}
