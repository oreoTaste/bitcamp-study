package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.ArrayList;

public class LessonHandler {
  ArrayList<Lesson> lessonList;
  public Scanner input;

  public LessonHandler(Scanner input) {
    this.input = input;
    lessonList = new ArrayList<>();
  }
  
  public void addLesson() {
    Object object = new Lesson();
    Lesson les = (Lesson)object;
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

    lessonList.add(les);
    System.out.println("저장하였습니다.");
  }

  public void listLesson() {
    Lesson[] arr = this.lessonList.toArray(new Lesson[this.lessonList.size()]);
    for(Lesson ls : arr){
      System.out.printf("%d, %s     , %tF ~ %tF, %d\n",
          ls.getNo(), ls.getTitle(), ls.getStartDate(), 
          ls.getEndDate(), ls.getTotalHour());
    }
  }
  

  public void updateLesson() {
    System.out.print("인덱스 번호? ");
    int idx = input.nextInt();
    input.nextLine();

    Lesson oldLesson = lessonList.get(idx);

    if(oldLesson == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }
    
    Lesson newLesson = new Lesson();
    System.out.printf("수업명(%s)? ", oldLesson.getTitle());
    String tempTitle = input.nextLine();
    
    System.out.printf("수업내용(%s)? ", oldLesson.getContext());
    String tempContext = input.nextLine();
    if(tempContext == "") {
      System.out.println("게시물 변경을 취소했습니다.");
      return;
    }
    
    System.out.printf("시작일? (%tF) ", oldLesson.getStartDate());
    Date tempStartDate= Date.valueOf(input.nextLine());
    
    System.out.printf("종료일? (%tF) ", oldLesson.getStartDate());
    Date tempEndDate= Date.valueOf(input.nextLine());
    
    System.out.printf("총수업시간? (%s) ", oldLesson.getTotalHour());
    int tempTotalHour = input.nextInt();
    
    System.out.printf("일수업시간? (%s) ", oldLesson.getDailyHour());
    int tempDailyHour = input.nextInt();
    
    newLesson.setTitle(tempTitle);
    newLesson.setContext(tempContext);
    newLesson.setStartDate(tempStartDate);
    newLesson.setStartDate(tempEndDate);
    newLesson.setTotalHour(tempTotalHour);
    newLesson.setTotalHour(tempDailyHour);
    
    this.lessonList.set(idx, newLesson);
    System.out.println("게시글을 변경했습니다.");
  }

  public void deleteLesson() {
    System.out.print("인덱스 번호? ");
    int idx = input.nextInt();
    input.nextLine();

    Lesson lesson = lessonList.get(idx);

    if(lesson == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }

    this.lessonList.remove(idx, lesson);
    System.out.println("게시글을 삭제했습니다.");
  }
  
}
