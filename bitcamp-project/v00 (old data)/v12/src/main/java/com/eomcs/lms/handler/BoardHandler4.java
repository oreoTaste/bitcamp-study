package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler4 {

  static final int SIZE = 100;
  static int boardsCount = 0;
  static Board[] boards = new Board[SIZE];
  public static Scanner scanner;

  public static void addBoard() {
    Board board = new Board();
    System.out.print("번호? ");
    board.no = scanner.nextInt();
    scanner.nextLine(); // 줄바꿈 기호 제거용
    System.out.print("내용? ");
    board.title = scanner.nextLine();
    board.date = new Date(System.currentTimeMillis());
    board.viewCount    = 0;

    boards[boardsCount++] = board;
    System.out.println("저장하였습니다.");
  }
  
  //public을 붙임 (클래스멤버 접근제어!)
  public static void listBoard() {
    for (int i = 0; i < boardsCount; i++) {
      Board brd = boards[i];
      System.out.printf("%d, %s, %s, %d\n", 
          brd.no, brd.title, brd.date, brd.viewCount);
    }
  }
  
  public static void detailBoard() {
    System.out.print("게시물 번호? ");
    int no = scanner.nextInt();
    scanner.nextLine();
    
    Board board = null;
    for(int i = 0 ; i < boardsCount ; i++) {
      if(boards[i].no == no) {
        board = boards[i];
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
