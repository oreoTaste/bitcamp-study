package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.List;
import com.eomcs.util.Prompt;

public class BoardHandler {
  List<Board> boardList;
  public Scanner input;
  public Prompt prompt;

  public BoardHandler(final Prompt prompt, final List<Board> list) {
    this.prompt = prompt;
    boardList = list;
  }

  /*
   * public BoardHandler(Prompt prompt, int capacity) { this.prompt = prompt; boardList = new
   * LinkedList<>(capacity); }
   */
  public void addBoard() {
    final Board board = new Board();
    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("내용? "));
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    boardList.add(board);
  }

  public void deleteBoard() {
    final int index = indexOfBoard(prompt.inputInt("번호? "));
    if (index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return;
    }
    boardList.remove(index);
    System.out.println("게시글을 삭제했습니다.");
  }

  public void detailBoard() {
    final int no = prompt.inputInt("번호? ");
    final int index = indexOfBoard(no);

    if (index == -1) {
      System.out.println("해당번호의 게시글이 없습니다.");
      return;
    } else {
      final Board board = boardList.get(index);
      System.out.printf("번호 : %d\n", board.getNo());
      System.out.printf("제목 : %s\n", board.getTitle());
      System.out.printf("등록일 : %tF\n", board.getDate());
      System.out.printf("조회수 : %d\n", board.getViewCount());
    }
  }

  private int indexOfBoard(final int no) {
    for (int i = 0; i < boardList.size(); i++) {
      if (boardList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }


  public void listBoard() {
    final Board[] board = new Board[boardList.size()];
    boardList.toArray(board);
    for (final Board b : board) {
      System.out.printf("%d, %s, %s, %d\n", b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
    }
  }

  public void updateBoard() {
    final int no = prompt.inputInt("번호? ");
    final int index = indexOfBoard(no);
    if (index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return;
    }

    final Board oldBoard = boardList.get(index);
    final Board newBoard = new Board();

    // System.out.printf("번호? %d\n", oldBoard.getNo());
    newBoard.setNo(oldBoard.getNo());

    newBoard.setTitle(
        prompt.inputString(String.format("내용? (%s) ", oldBoard.getTitle()), oldBoard.getTitle()));

    // System.out.printf("등록일? %1$tF %1$tH:%1$tM:%1$tS\n", oldBoard.getDate());
    newBoard.setDate(oldBoard.getDate());

    // System.out.printf("조회수? %d\n", oldBoard.getViewCount());
    newBoard.setViewCount(0);

    if (oldBoard.equals(newBoard)) {
      System.out.println("게시글 변경을 취소했습니다.");
    } else {
      boardList.set(index, newBoard);
      System.out.println("게시글을 변경했습니다.");
    }
  }


}
