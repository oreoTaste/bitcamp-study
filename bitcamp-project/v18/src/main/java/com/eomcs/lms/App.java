package com.eomcs.lms;

import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;

public class App {
  static java.io.InputStream inputStream = System.in;
  static java.util.Scanner keyboard = new java.util.Scanner(inputStream);

  public static void main(String[] args) {
    // LessonHandler, MemberHandler, BoardHandler에 있는 Scanner는 
    // App에있는 Scanner를 사용한다.(이때, public인 것을 받아온다)

   //BrandHenlder 의 메서드가 사용될 메모리만 게시판마다 따로 생성한다.
    BoardHandler boardHandler = new BoardHandler(keyboard);
    LessonHandler lessonHandler = new LessonHandler(keyboard);
    MemberHandler memberHandler = new MemberHandler(keyboard);
    
    String command;

    do {
      System.out.println();
      System.out.print("명령> ");
      command = keyboard.nextLine();

      switch (command) {
        case "/lesson/add" :
          //다른 클래스로 분리함. (이때 클래스 이름 지정해야함)
          lessonHandler.addLesson();
          break;

        case "/lesson/list" :
          lessonHandler.listLesson();
          break;

        case "/member/add" :
          memberHandler.addMember();
          break;

        case "/member/list" :
          memberHandler.listMember();
          break;

        case "/board/add" :
          boardHandler.addBoard();
          break;

        case "/board/list" :
          boardHandler.listBoard();
          break;
          
        case "/board/detail" :
          boardHandler.detailBoard();
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