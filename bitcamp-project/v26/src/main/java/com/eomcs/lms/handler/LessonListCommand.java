package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Lesson;

public class LessonListCommand implements Command {
  List<Lesson> lessonList;

  public LessonListCommand(List<Lesson> list) {
    lessonList = list;
  }

  @Override
  public void execute() {
    Lesson[] lesson = new Lesson[lessonList.size()];
    lessonList.toArray(lesson);
    for (Lesson ls : lesson) {
      System.out.printf("%d, %s     , %tF ~ %tF, %d\n", ls.getNo(), ls.getTitle(),
          ls.getStartDate(), ls.getEndDate(), ls.getTotalHour());
    }
  }


}
