package com.eomcs.lms.handler;

import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.prompt.Prompt;

public class LessonDeleteCommand implements Command {
  LessonDao lessonDao;
  Prompt prompt;

  public LessonDeleteCommand(LessonDao lessonDao, Prompt prompt) {
    this.prompt = prompt;
    this.lessonDao = lessonDao;
  }


  @Override
  public void execute() {
    try {
    int no = prompt.inputInt("번호? ");
      lessonDao.delete(no);
      
      System.out.println("수업을 삭제했습니다.");
      
    } catch (Exception e) {
      System.out.println("수업정보 삭제중 오류발생!");
    }
    
  }




}
