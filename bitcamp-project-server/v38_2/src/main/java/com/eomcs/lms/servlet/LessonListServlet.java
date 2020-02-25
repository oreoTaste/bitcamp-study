package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonListServlet implements Servlet {

  LessonDao lessonDao;
  
  public LessonListServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    List<Lesson> lesson = lessonDao.findAll();
    
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
