package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonListCommand implements Command {
  LessonDao lessonDao;

  public LessonListCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute() {

    try {

      List<Lesson> lesson = lessonDao.findAll();
      
      for (Lesson ls : lesson) {
        System.out.printf("%d, %s, %s, %tF ~ %tF, %d, %d\n",
        ls.getNo(),
        ls.getTitle(),
        ls.getContext(),
        ls.getStartDate(),
        ls.getEndDate(),
        ls.getTotalHour(),
        ls.getDailyHour());
      }
    } catch (Exception e) {
      System.out.println("수업 리스트 수신중 오류 발생!");
    }


  }
}
