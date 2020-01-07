package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {

  // 인스턴스 필드
  // new를 타이핑쳐야만 생성되는 변수다.
  // 개별적으로 관리되는 값일 경우, 인스턴스 필드로 선언한다.
  int boardsCount = 0;
  Board[] boards = new Board[SIZE];

  // 클래스필드(스태틱 필드)
  // 클래스 코드가 로딩될때 자동생성된다.
  // 공통으로 사용할 값일 경우, 클래스 필드로 선언한다.
  public static Scanner scanner;
  static final int SIZE = 100;
  
  // 클래스 메서드
  public void addBoard() {
    Board board = new Board();
    System.out.print("번호? ");
    board.no = scanner.nextInt();
    scanner.nextLine(); // 줄바꿈 기호 제거용
    System.out.print("내용? ");
    board.title = scanner.nextLine();
    board.date = new Date(System.currentTimeMillis());
    board.viewCount = 0;
    
    this.boards[this.boardsCount++] = board;
    System.out.println("저장하였습니다.");
  }
  
  //public을 붙임 (클래스멤버 접근제어!)
  public void listBoard() {
    for (int i = 0; i < this.boardsCount; i++) {
      Board brd = this.boards[i];
      System.out.printf("%d, %s, %s, %d\n", 
          brd.no, brd.title, brd.date, brd.viewCount);
    }
  }
  
  public void detailBoard() {
    System.out.print("게시물 번호? ");
    int no = scanner.nextInt();
    scanner.nextLine();
    
    Board board = null;
    for(int i = 0 ; i < this.boardsCount ; i++) {
      if(this.boards[i].no == no) {
        board = this.boards[i];
        break;
      }
    }
    
    if(board == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }
    
    System.out.printf("번호 : %d\n", board.no);
    System.out.printf("제목 : %s\n", board.title);
    System.out.printf("등록일 : %tF\n", board.date);
    System.out.printf("주회수 : %d\n", board.viewCount);
  }
}
