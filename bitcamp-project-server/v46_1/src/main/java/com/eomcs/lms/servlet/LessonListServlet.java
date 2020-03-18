package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.Component;

@Component("/lesson/list")
public class LessonListServlet implements Servlet {

  LessonService lessonService;
  
  public LessonListServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }
  
  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    List<Lesson> lesson = lessonService.list();
    
    for (Lesson ls : lesson) {
      out.printf("%d, \t%-8s, \t%s, %tF ~ %tF, %d, %d\n",
      ls.getNo(),
      ls.getTitle(),
      ls.getContext(),
      ls.getStartDate(),
      ls.getEndDate(),
      ls.getTotalHour(),
      ls.getDailyHour());
    }
  }

}
