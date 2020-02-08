package com.eomcs.lms.handler;

import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.prompt.Prompt;

public class LessonUpdateCommand implements Command {
  LessonDao lessonDao;
  Prompt prompt;

  public LessonUpdateCommand(LessonDao lessonDao, Prompt prompt) {
    this.prompt = prompt;
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute() {
    int no = prompt.inputInt("번호? ");

    try {
      Lesson oldLesson = lessonDao.findByNo(no);
      Lesson newLesson = new Lesson();

      newLesson.setNo(oldLesson.getNo());

      newLesson.setTitle(prompt.inputString(String.format("수업명? (%s) ", oldLesson.getTitle()),
          oldLesson.getTitle()));

      newLesson.setContext(prompt.inputString(String.format("수업내용? (%s) ", oldLesson.getContext()),
          oldLesson.getContext()));

      newLesson.setStartDate(prompt.inputDate(String.format("시작일? (%s) ", oldLesson.getStartDate()),
          oldLesson.getStartDate()));

      newLesson.setEndDate(prompt.inputDate(String.format("종료일? (%s) ", oldLesson.getEndDate()),
          oldLesson.getEndDate()));

      newLesson.setTotalHour(prompt.inputInt(String.format("총수업시간? (%d) ", oldLesson.getTotalHour()),
          oldLesson.getTotalHour()));

      newLesson.setDailyHour(prompt.inputInt(String.format("일수업시간? (%d) ", oldLesson.getDailyHour()),
          oldLesson.getDailyHour()));

      if (newLesson.equals(oldLesson)) {
        System.out.println("수업 변경을 취소했습니다.");
        return;
      }
        lessonDao.update(newLesson);
        System.out.println("수업을 변경했습니다.");
        
    } catch (Exception e) {
      System.out.println("수업정보 업데이트 중 오류발생");
    }
  }


}
