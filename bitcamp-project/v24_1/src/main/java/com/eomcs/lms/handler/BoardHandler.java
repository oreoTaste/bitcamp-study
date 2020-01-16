package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Iterator;
import com.eomcs.util.List;
import com.eomcs.util.Prompt;

public class BoardHandler{
  List<Board> boardList;
  public Scanner input;
  public Prompt prompt;

  public BoardHandler(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    boardList = list;
  }
  /*
  public BoardHandler(Prompt prompt, int capacity) {
    this.prompt = prompt;
    boardList = new LinkedList<>(capacity);
  }
   */
  public void addBoard() {
    Board board = new Board();
    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("내용? "));
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    boardList.add(board);
  }

  public void listBoard() {
    Iterator<Board> iterator = boardList.iterator();
    while(iterator.hasNext()) {
      Board b = iterator.next();
      
        System.out.printf("%d, %s, %s, %d\n", 
            b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
      }
    }

  public void detailBoard() {
    int no = prompt.inputInt("번호? ");
    int index = indexOfBoard(no);

    if (index == -1) {
      System.out.println("해당번호의 게시글이 없습니다.");
      return;
    } else {
      Board board = this.boardList.get(index);
      System.out.printf("번호 : %d\n", board.getNo());
      System.out.printf("제목 : %s\n", board.getTitle());
      System.out.printf("등록일 : %tF\n", board.getDate());
      System.out.printf("조회수 : %d\n", board.getViewCount());
    }
  }

  public void updateBoard() {
    int no = prompt.inputInt("번호? ");
    int index = indexOfBoard(no);
    if(index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return;
    } 

    Board oldBoard = this.boardList.get(index);
    Board newBoard = new Board();

    //System.out.printf("번호? %d\n", oldBoard.getNo());
    newBoard.setNo(oldBoard.getNo());

    newBoard.setTitle(prompt.inputString(
        String.format("내용? (%s) ", oldBoard.getTitle()),
        oldBoard.getTitle()));

    //System.out.printf("등록일? %1$tF %1$tH:%1$tM:%1$tS\n", oldBoard.getDate());
    newBoard.setDate(oldBoard.getDate());

    //System.out.printf("조회수? %d\n", oldBoard.getViewCount());
    newBoard.setViewCount(0);

    if(oldBoard.equals(newBoard)) {
      System.out.println("게시글 변경을 취소했습니다.");
    } else {
      boardList.set(index, newBoard);
      System.out.println("게시글을 변경했습니다.");
    } 
  }


  public void deleteBoard() {
    int index = indexOfBoard(prompt.inputInt("번호? "));
    if(index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return;
    } 
    this.boardList.remove(index);
    System.out.println("게시글을 삭제했습니다.");
  }

  private int indexOfBoard(int no) {
    for(int i = 0 ; i < this.boardList.size() ; i++) {
      if(this.boardList.get(i).getNo() == no) {
        return i;
      }
    } return -1;
  }


}
