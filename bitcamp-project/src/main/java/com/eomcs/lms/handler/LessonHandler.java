package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.List;
import com.eomcs.util.Prompt;

public class LessonHandler {
  List<Lesson> lessonList;
  public Prompt prompt;

  public LessonHandler(final Prompt prompt, final List<Lesson> list) {
    this.prompt = prompt;
    lessonList = list;
  }
  /*
   * public LessonHandler(Prompt prompt, int capacity) { this.prompt = prompt; lessonList = new
   * LinkedList<>(capacity); }
   */

  public void addLesson() {
    final Lesson lesson = new Lesson();

    lesson.setNo(prompt.inputInt("번호? "));
    lesson.setTitle(prompt.inputString("수업명? "));
    lesson.setContext(prompt.inputString("수업내용? "));
    lesson.setStartDate(prompt.inputDate("시작일? (형식 : 2019-01-01) "));
    lesson.setEndDate(prompt.inputDate("종료일? (형식 : 2019-01-01) "));
    lesson.setTotalHour(prompt.inputInt("총수업시간? (형식: 100) "));
    lesson.setDailyHour(prompt.inputInt("일수업시간? (형식: 8) "));
    System.out.println();

    lessonList.add(lesson);
  }

  public void deleteLesson() {
    System.out.print("번호? ");
    final int no = prompt.input.nextInt();
    prompt.input.nextLine(); // 숫자 뒤의 남은 공백 제거

    final int index = indexOfLesson(no);

    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }

    lessonList.remove(index);

    System.out.println("수업을 삭제했습니다.");
  }

  public void detailLesson() {
    System.out.print("번호? ");
    final int no = prompt.input.nextInt();
    prompt.input.nextLine(); // 숫자 뒤의 남은 공백 제거

    final int index = indexOfLesson(no);

    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }

    final Lesson ls = lessonList.get(index);
    System.out.printf("번호: %d\n", ls.getNo());
    System.out.printf("수업명: %s\n", ls.getTitle());
    System.out.printf("수업내용: %s\n", ls.getContext());
    System.out.printf("기간: %tF ~ %tF\n", ls.getStartDate(), ls.getEndDate());
    System.out.printf("총수업시간: %d\n", ls.getTotalHour());
    System.out.printf("일수업시간: %d\n", ls.getDailyHour());
  }

  // 공부하기
  private int indexOfLesson(final int no) {
    for (int i = 0; i < lessonList.size(); i++) {
      if (lessonList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }


  public void listLesson() {
    final Lesson[] lesson = new Lesson[lessonList.size()];
    lessonList.toArray(lesson);
    for (final Lesson ls : lesson) {
      System.out.printf("%d, %s     , %tF ~ %tF, %d\n", ls.getNo(), ls.getTitle(),
          ls.getStartDate(), ls.getEndDate(), ls.getTotalHour());
    }
  }



  public void updateLesson() {
    System.out.print("번호? ");
    final int no = prompt.input.nextInt();
    prompt.input.nextLine(); // 숫자 뒤의 남은 공백 제거

    final int index = indexOfLesson(no);

    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }

    final Lesson oldLesson = lessonList.get(index);
    final Lesson newLesson = new Lesson();

    // System.out.printf("번호? %d\n", oldLesson.getNo());
    newLesson.setNo(oldLesson.getNo());

    newLesson.setTitle(
        prompt.inputString(String.format("수업명? (%s)", oldLesson.getTitle()), oldLesson.getTitle()));

    newLesson.setContext(prompt.inputString(String.format("수업내용? ", oldLesson.getContext()),
        oldLesson.getContext()));

    newLesson.setStartDate(prompt.inputDate(String.format("시작일? (%tF)", oldLesson.getStartDate()),
        oldLesson.getStartDate()));

    newLesson.setEndDate(prompt.inputDate(String.format("종료일? (%tF)", oldLesson.getEndDate()),
        oldLesson.getEndDate()));

    newLesson.setTotalHour(prompt.inputInt(String.format("총수업시간? (%d)", oldLesson.getTotalHour()),
        oldLesson.getTotalHour()));

    newLesson.setDailyHour(prompt.inputInt(String.format("일수업시간? (%d)", oldLesson.getDailyHour()),
        oldLesson.getDailyHour()));

    System.out.println();

    if (oldLesson.equals(newLesson)) {
      System.out.println("수업 변경을 취소했습니다.");
    } else {
      lessonList.set(index, newLesson);
      System.out.println("수업을 변경했습니다.");
    }
  }

}
