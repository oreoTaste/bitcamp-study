package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardUpdateCommand implements Command {
  List<Board> boardList;
  Prompt prompt;

  public BoardUpdateCommand(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    boardList = list;
  }

  @Override
  public void execute() {

    int no = prompt.inputInt("번호? ");
    int index = indexOfBoard(no);

    if (index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
    }

    Board oldBoard = boardList.get(index);
    Board newBoard = new Board();

    newBoard.setNo(oldBoard.getNo());

    newBoard.setTitle(
        prompt.inputString(String.format("제목? (%s) ", oldBoard.getTitle()), oldBoard.getTitle()));

    newBoard.setDate(new Date(System.currentTimeMillis()));

    newBoard.setViewCount(0);

    if (newBoard.equals(oldBoard)) {
      System.out.println("게시글 변경을 취소했습니다.");
    } else {
      boardList.set(index, newBoard);
      System.out.println("게시글을 변경했습니다.");
    }
  }

  public int indexOfBoard(int no) {
    for (int i = 0; i < boardList.size(); i++) {
      if (boardList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }

}
