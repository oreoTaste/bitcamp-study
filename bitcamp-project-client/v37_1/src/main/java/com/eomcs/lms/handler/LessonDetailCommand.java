package com.eomcs.lms.handler;

import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.prompt.Prompt;

public class LessonDetailCommand implements Command {
  LessonDao lessonDao;
  Prompt prompt;

  public LessonDetailCommand(LessonDao lessonDao, Prompt prompt) {
    this.prompt = prompt;
    this.lessonDao = lessonDao;
  }


  @Override
  public void execute() {
    int no = prompt.inputInt("번호? ");

    try {
      Lesson lesson = lessonDao.findByNo(no);

      System.out.printf("번호? %d\n", lesson.getNo());
      System.out.printf("수업명: %s\n", lesson.getTitle());
      System.out.printf("수업내용: %s\n", lesson.getContext());
      System.out.printf("기간 : %tF ~ %tF\n", lesson.getStartDate(), lesson.getEndDate());
      System.out.printf("총수업시간: %d\n", lesson.getTotalHour());
      System.out.printf("일수업시간: %d\n", lesson.getDailyHour());
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("수업정보 세부사항 수신 중 오류발생");
    }
  }



}
