package com.eomcs.lms;

import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.util.Prompt;
import com.eomcs.util.Stack;

public class App { 
  static java.io.InputStream inputStream = System.in;
  static java.util.Scanner keyboard = new java.util.Scanner(inputStream);
  static Stack commandStack = new Stack();

  public static void main(String[] args) {
    Prompt prompt = new Prompt(keyboard);
    BoardHandler boardHandler = new BoardHandler(prompt);
    LessonHandler lessonHandler = new LessonHandler(prompt);
    MemberHandler memberHandler = new MemberHandler(prompt);

    String command;

    do {
      System.out.println();
      System.out.print("명령> ");
      command = keyboard.nextLine();
      if(command.length() == 0)
        continue;
      commandStack.push(command);

      switch (command) {
        case "/lesson/add" :
          lessonHandler.addLesson();
          break;

        case "/lesson/list" :
          lessonHandler.listLesson();
          break;

        case "/lesson/update" :
          lessonHandler.updateLesson();
          break;

        case "/lesson/delete" :
          lessonHandler.deleteLesson();
          break;

        case "/lesson/detail" :
          lessonHandler.detailLesson();
          break;

        case "/member/add" :
          memberHandler.addMember();
          break;

        case "/member/list" :
          memberHandler.listMember();
          break;

          // 새로운 구문 추가 (detail) : 추가연습
        case "/member/detail" :
          memberHandler.detailMember();
          break;

        case "/member/update" :
          memberHandler.updateMember();
          break;

        case "/member/delete" :
          memberHandler.deleteMember();
          break;

        case "/board/add" :
          boardHandler.addBoard();
          break;

        case "/board/list" :
          boardHandler.listBoard();
          break;

          // 새로운 구문 추가 (detail) : 추가연습
        case "/board/detail" :
          boardHandler.detailBoard();
          break;

        case "/board/update" :
          boardHandler.updateBoard();
          break;

        case "/board/delete" :
          boardHandler.deleteBoard();
          break;

        case "history" :
          printCommandHistory();
          break;

        default : 
          if(!command.equalsIgnoreCase("quit"))
            System.out.println("실행할 수 없는 명령입니다.");
          break;
      }

    } while (!command.equalsIgnoreCase("quit"));
    keyboard.close();
    System.out.println("...안녕!");
  }

  
  // Object.clone()의 shallow copy를 이용하여 스택 객체 복사하기
  // 문제점 :
  // 데이터가 실제 저장된 배열은 복제하지 않는다.
  // 따라서, 배열의 값을 바꾸면 원본스택에도 영향을 받는다.
  
  private static void printCommandHistory() {
    Stack historyStack = commandStack.clone(); // soft-clone
    int count = 0;
    while(!historyStack.empty()) {
      System.out.println(historyStack.pop());
      count++;
      if((count % 5) == 0) {
        System.out.print(": (중지하고 싶으면 q)");
        String str = keyboard.nextLine();
        if(str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

}
/*
명령>

명령> /lesson/add
번호? 1
수업명? 자바 프로젝트 실습
수업내용? 자바 프로젝트를 통한 자바 언어 활용법 익히기
시작일? 2019-01-02
종료일? 2019-05-28
총수업시간? 1000
일수업시간? 8
저장하였습니다.

명령> /lesson/list
1, 자바 프로젝트 실습     , 2019-01-02 ~ 2019-05-28, 1000
2, 자바 프로그래밍 기초    , 2019-02-01 ~ 2019-02-28,  160
3, 자바 프로그래밍 고급    , 2019-03-02 ~ 2019-03-30,  160

명령> board/view
실행할 수 없는 명령입니다.

명령> quit
안녕!
 */