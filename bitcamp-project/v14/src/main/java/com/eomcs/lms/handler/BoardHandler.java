package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {

  // 인스턴스 필드
  // new를 타이핑쳐야만 생성되는 변수다.
  // 개별적으로 관리되는 값일 경우, 인스턴스 필드로 선언한다.
  int boardsCount = 0;
  Board[] boards;

  // 클래스필드(스태틱 필드)
  // 클래스 코드가 로딩될때 자동생성된다.
  // 공통으로 사용할 값일 경우, 클래스 필드로 선언한다.
  static final int SIZE = 100;
  // 키보드뿐만 아니라 네트워크, 파일로부터도 입력받을 수 있음.
  public Scanner input;

  // 생성자(Constructor) 입력!
  public BoardHandler(Scanner input) {
    // 생성자의 역할 : 의존객체 초기화.
    // 의존객체 (dependency object 혹은 dependency)
    this.input = input;
    this.boards = new Board[SIZE];
  }

  public BoardHandler(Scanner input, int capacity) {
    this.input = input;
    if(capacity < SIZE || capacity > 10000)
      this.boards = new Board[SIZE];
    else
      this.boards = new Board[capacity];
  }

  // 클래스 메서드
  public void addBoard() {
    Board board = new Board();
    System.out.print("번호? ");
    board.no = input.nextInt();
    input.nextLine(); // 줄바꿈 기호 제거용
    System.out.print("내용? ");
    board.title = input.nextLine();
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
    int no = input.nextInt();
    input.nextLine();

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
