package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.Prompt;
import com.eomcs.util.RequestMapping;

@Component
public class LessonAddServlet {

  LessonService lessonService;

  public LessonAddServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @RequestMapping("/lesson/add")
  public void service(Scanner in, PrintStream out) throws Exception {
    try {
      Lesson lesson = new Lesson();
      
      lesson.setTitle(Prompt.getString(in, out, "수업명? "));
      lesson.setContext(Prompt.getString(in, out, "수업내용? "));
      lesson.setStartDate(Prompt.getDate(in, out, "시작일? (형식 : 2019-01-01) "));
      lesson.setEndDate(Prompt.getDate(in, out, "종료일? (형식 : 2019-01-01) "));
      lesson.setTotalHour(Prompt.getInt(in, out, "총수업시간? (형식: 1000) "));
      lesson.setDailyHour(Prompt.getInt(in, out, "일수업시간? (형식: 8) "));

      if (lessonService.add(lesson)){
        out.println("새 글을 등록했습니다.");

      } else {
        out.println("수업정보 등록에 실패했습니다.");
      }

    } catch (Exception e) {
      System.out.println("수업정도 추가중 오류발생");
    }
  }
}
