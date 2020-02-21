package com.eomcs.lms.handler;

import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.prompt.Prompt;

public class LessonAddCommand implements Command {
  LessonDao lessonDao;
  Prompt prompt;

  public LessonAddCommand(LessonDao lessonDao, Prompt prompt) {
    this.prompt = prompt;
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute() {
    Lesson lesson = new Lesson();

    try {
      lesson.setNo(prompt.inputInt("번호? "));
      lesson.setTitle(prompt.inputString("수업명? "));
      lesson.setContext(prompt.inputString("수업내용? "));
      lesson.setStartDate(prompt.inputDate("시작일? (형식 : 2019-01-01) "));
      lesson.setEndDate(prompt.inputDate("종료일? (형식 : 2019-01-01) "));
      lesson.setTotalHour(prompt.inputInt("총수업시간? (형식: 1000) "));
      lesson.setDailyHour(prompt.inputInt("일수업시간? (형식: 8) "));
      
      lessonDao.insert(lesson);
      
    } catch (Exception e) {
      System.out.println("수업정도 추가중 오류발생");
    }

  }


}
