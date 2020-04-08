package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.Component;
import com.eomcs.util.Prompt;

@Component("/lesson/delete")
public class LessonDeleteServlet implements Servlet {

  LessonService lessonService;

  public LessonDeleteServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    try {
      int no = Prompt.getInt(in, out, "번호?");
      
      if(lessonService.delete(no)) {
        out.println("수업을 삭제했습니다.");
      } else {
        out.println("해당 번호의 수업이 없습니다");
      }

    } catch (Exception e) {
      out.println("수업정보 삭제중 오류발생!");
    }
  }
}
