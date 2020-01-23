package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonHandler {
  //개별 관리
  int lessonsCount = 0;
  Lesson[] lessons;
  
  //공유 관리
  static final int SIZE = 100;
  public Scanner input;
  
  public LessonHandler(Scanner input) {
    this.input = input;
    lessons = new Lesson[SIZE];
  }
  
  public void addLesson() {
    Lesson les = new Lesson();
    System.out.print("번호? ");
    les.setNo(input.nextInt());
    input.nextLine();
    System.out.print("수업명? ");
    les.setTitle(input.nextLine());
    System.out.print("수업내용? ");
    les.setContext(input.nextLine());
    System.out.print("시작일? (형식 : 2019-01-01) ");
    les.setStartDate(Date.valueOf(input.nextLine()));
    System.out.print("종료일? (형식 : 2019-01-01) ");
    les.setEndDate(Date.valueOf(input.nextLine()));
    System.out.print("총수업시간? (형식: 1000) ");
    les.setTotalHour(input.nextInt());
    System.out.print("일수업시간? (형식: 8) ");
    les.setDailyHour(input.nextInt());
    System.out.println();
    input.nextLine();
    
    
    this.lessons[this.lessonsCount++] = les;
    System.out.println("저장하였습니다.");
  }
  public void listLesson() {
    for(int i=0 ; i < this.lessonsCount ; i++){
      Lesson ls = this.lessons[i];
      System.out.printf("%d, %s     , %tF ~ %tF, %d\n",
          ls.getNo(), ls.getTitle(), ls.getStartDate(), 
          ls.getEndDate(), ls.getTotalHour());
    }
  }
  
}
