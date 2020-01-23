package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {
  ArrayList boardList;

  public Scanner input;

  public BoardHandler(Scanner input) {
    this.input = input;
    boardList= new ArrayList();
  }

  public BoardHandler(Scanner input, int size) {
    this.input = input;
    boardList= new ArrayList(size);
  }
  
  public void addBoard() {
    Object object = new Board();
    Board board = (Board) object;
    System.out.print("번호? ");
    board.setNo(input.nextInt());
    input.nextLine(); // 줄바꿈 기호 제거용
    System.out.print("내용? ");
    board.setTitle(input.nextLine());
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);
    
    boardList.add(board);
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    Object[] arr = boardList.toArray();
    for (Object obj : arr) {
      Board b = (Board)obj;
      System.out.printf("%d, %s, %s, %d\n", 
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
    }
  }

  public void detailBoard() {
    System.out.print("게시물 인덱스? ");
    int idx = input.nextInt();
    input.nextLine();

    Board board = (Board)this.boardList.get(idx);

    if(board == null) {
      System.out.println("게시물 인덱스가 유효하지 않습니다.");
      return;
    }

    System.out.printf("번호 : %d\n", board.getNo());
    System.out.printf("제목 : %s\n", board.getTitle());
    System.out.printf("등록일 : %tF\n", board.getDate());
    System.out.printf("주회수 : %d\n", board.getViewCount());
  }
}
