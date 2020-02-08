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

    List<Lesson> lesson;
    try {
      lesson = lessonDao.findAll();
      for (Lesson ls : lesson) {
        System.out.printf("%d, %s     , %tF ~ %tF, %d\n", ls.getNo(), ls.getTitle(),
            ls.getStartDate(), ls.getEndDate(), ls.getTotalHour());
      }
    } catch (Exception e) {
      System.out.println("수업 리스트 수신중 오류 발생!");
    }
  }


}
