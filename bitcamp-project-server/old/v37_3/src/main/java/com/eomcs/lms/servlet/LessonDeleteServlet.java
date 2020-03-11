package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;

public class LessonDeleteServlet implements Servlet {

  LessonDao lessonDao;

  public LessonDeleteServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    try {
      out.println("번호?\n!{}!");
      out.flush();
      int no = Integer.parseInt(in.nextLine());
      
      if(lessonDao.delete(no) > 0) {
        System.out.println("수업을 삭제했습니다.");
      } else {
        System.out.println("해당 번호의 수업이 없습니다");
      }

    } catch (Exception e) {
      System.out.println("수업정보 삭제중 오류발생!");
    }
  }
}
