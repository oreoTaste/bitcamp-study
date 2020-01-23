package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.ArrayList;

public class BoardHandler {
  ArrayList<Board> boardList;

  public Scanner input;

  public BoardHandler(Scanner input) {
    this.input = input;
    this.boardList= new ArrayList<>();
  }

  public BoardHandler(Scanner input, int size) {
    this.input = input;
    this.boardList= new ArrayList<>(size);
  }
  
  public void addBoard() {
    Board board = new Board();
    System.out.print("번호? ");
    board.setNo(input.nextInt());
    input.nextLine(); // 줄바꿈 기호 제거용
    System.out.print("내용? ");
    board.setTitle(input.nextLine());
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);
    
    this.boardList.add(board);
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    Board[] arr = new Board[this.boardList.size()];
     this.boardList.toArray(arr);
    
    for (Board brd : arr) {
      System.out.printf("%d, %s, %s, %d\n", 
          brd.getNo(), brd.getTitle(), brd.getDate(), brd.getViewCount());
    }
  }

  public void detailBoard() {
    System.out.print("인덱스 번호? ");
    int idx = input.nextInt();
    input.nextLine();

    Board board = boardList.get(idx);

    if(board == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }

    System.out.printf("번호 : %d\n", board.getNo());
    System.out.printf("제목 : %s\n", board.getTitle());
    System.out.printf("등록일 : %tF\n", board.getDate());
    System.out.printf("주회수 : %d\n", board.getViewCount());
  }
}
