package com.eomcs.lms;

import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;

public class App {
  static java.io.InputStream inputStream = System.in;
  static java.util.Scanner keyboard = new java.util.Scanner(inputStream);

  static final int SIZE = 100;

  public static void main(String[] args) {
    // LessonHandler, MemberHandler, BoardHandler에 있는 Scanner는 
    // App에있는 Scanner를 사용한다.(이때, public인 것을 받아온다)

    
   //BrandHenlder 의 메서드가 사용될 메모리만 게시판마다 따로 생성한다.
    BoardHandler 게시판1 = new BoardHandler(keyboard);
    BoardHandler 게시판2 = new BoardHandler(keyboard);
    BoardHandler 게시판3 = new BoardHandler(keyboard);
    BoardHandler 게시판4 = new BoardHandler(keyboard);
    BoardHandler 게시판5 = new BoardHandler(keyboard);
    BoardHandler 게시판6 = new BoardHandler(keyboard);
    LessonHandler 정규수업 = new LessonHandler(keyboard);
    MemberHandler 일반회원 = new MemberHandler(keyboard);
    
    String command;

    do {
      System.out.println();
      System.out.print("명령> ");
      command = keyboard.nextLine();

      switch (command) {
        case "/lesson/add" :
          //다른 클래스로 분리함. (이때 클래스 이름 지정해야함)
          정규수업.addLesson();
          break;

        case "/lesson/list" :
          정규수업.listLesson();
          break;

        case "/member/add" :
          일반회원.addMember();
          break;

        case "/member/list" :
          일반회원.listMember();
          break;

        case "/board/add" :
          게시판1.addBoard();
          break;

        case "/board/list" :
          게시판1.listBoard();
          break;
          
        case "/board/detail" :
          게시판1.detailBoard();
          break;
          
        case "/board2/add" :
          게시판2.addBoard();
          break;

        case "/board2/list" :
          게시판2.listBoard();
          break;
          
        case "/board2/detail" :
          게시판2.detailBoard();
          break;
          
        case "/board3/add" :
          게시판3.addBoard();
          break;

        case "/board3/list" :
          게시판3.listBoard();
          break;
          
        case "/board3/detail" :
          게시판3.detailBoard();
          break;
          
        case "/board4/add" :
          게시판4.addBoard();
          break;

        case "/board4/list" :
          게시판4.listBoard();
          break;
          
        case "/board4/detail" :
          게시판4.detailBoard();
          break;
          
        case "/board5/add" :
          게시판5.addBoard();
          break;

        case "/board5/list" :
          게시판5.listBoard();
          break;
          
        case "/board5/detail" :
          게시판5.detailBoard();
          break;
          
        case "/board6/add" :
          게시판6.addBoard();
          break;

        case "/board6/list" :
          게시판6.listBoard();
          break;
          
        case "/board6/detail" :
          게시판6.detailBoard();
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