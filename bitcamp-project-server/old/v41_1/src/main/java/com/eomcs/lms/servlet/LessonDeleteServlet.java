package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.util.Prompt;

public class LessonDeleteServlet implements Servlet {

  LessonDao lessonDao;

  public LessonDeleteServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    try {
      int no = Prompt.getInt(in, out, "번호?");
      
      if(lessonDao.delete(no) > 0) {
        out.println("수업을 삭제했습니다.");
      } else {
        out.println("해당 번호의 수업이 없습니다");
      }

    } catch (Exception e) {
      out.println("수업정보 삭제중 오류발생!");
    }
  }
}
