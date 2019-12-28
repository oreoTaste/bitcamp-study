package com.eomcs.lms;

import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.BoardHandler2;
import com.eomcs.lms.handler.BoardHandler3;
import com.eomcs.lms.handler.BoardHandler4;
import com.eomcs.lms.handler.BoardHandler5;
import com.eomcs.lms.handler.BoardHandler6;
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
    BoardHandler2.scanner = scanner;
    BoardHandler3.scanner = scanner;
    BoardHandler4.scanner = scanner;
    BoardHandler5.scanner = scanner;
    BoardHandler6.scanner = scanner;
    
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
          
        case "/board/detail" :
          BoardHandler.detailBoard();
          break;
          
        case "/board2/add" :
          BoardHandler2.addBoard();
          break;

        case "/board2/list" :
          BoardHandler2.listBoard();
          break;
          
        case "/board2/detail" :
          BoardHandler2.detailBoard();
          break;
          
        case "/board3/add" :
          BoardHandler3.addBoard();
          break;

        case "/board3/list" :
          BoardHandler3.listBoard();
          break;
          
        case "/board3/detail" :
          BoardHandler3.detailBoard();
          break;
          
        case "/board4/add" :
          BoardHandler4.addBoard();
          break;

        case "/board4/list" :
          BoardHandler4.listBoard();
          break;
          
        case "/board4/detail" :
          BoardHandler4.detailBoard();
          break;
          
        case "/board5/add" :
          BoardHandler5.addBoard();
          break;

        case "/board5/list" :
          BoardHandler5.listBoard();
          break;
          
        case "/board5/detail" :
          BoardHandler5.detailBoard();
          break;
          
        case "/board6/add" :
          BoardHandler6.addBoard();
          break;

        case "/board6/list" :
          BoardHandler6.listBoard();
          break;
          
        case "/board6/detail" :
          BoardHandler6.detailBoard();
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