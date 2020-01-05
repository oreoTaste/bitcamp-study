package com.eomcs.lms;

import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;

public class App {
  static java.io.InputStream inputStream = System.in;
  static java.util.Scanner scanner = new java.util.Scanner(inputStream);

  static final int SIZE = 100;

  public static void main(String[] args) {
    // LessonHandler, MemberHandler, BoardHandler에 있는 Scanner는 
    // App에있는 Scanner를 사용한다.(이때, public인 것을 받아온다)
    LessonHandler.scanner = scanner;
    MemberHandler.scanner = scanner;
    BoardHandler.scanner = scanner;
    
    String command;

    do {
      System.out.println();
      System.out.print("명령> ");
      command = scanner.nextLine();

      switch (command) {
        case "/lesson/add" :
          //다른 클래스로 분리함. (이때 클래스 이름 지정해야함)
          LessonHandler.addLesson();
          break;

        case "/lesson/list" :
          LessonHandler.listLesson();
          break;

        case "/member/add" :
          MemberHandler.addMember();
          break;

        case "/member/list" :
          MemberHandler.listMember();
          break;

        case "/board/add" :
          BoardHandler.addBoard();
          break;

        case "/board/list" :
          BoardHandler.listBoard();
          break;

        default : 
          if(!command.equalsIgnoreCase("quit"))
            System.out.println("실행할 수 없는 명령입니다.");
          break;
      }

    } while (!command.equalsIgnoreCase("quit"));
    scanner.close();
    System.out.println("...안녕!");
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