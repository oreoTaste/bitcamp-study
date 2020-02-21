package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.prompt.Prompt;

// "/board/update" 명령 처리
public class BoardUpdateCommand implements Command {

  BoardDao boardDao;
  Prompt prompt;

  public BoardUpdateCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Board oldBoard = boardDao.findByNo(no);
      Board newBoard = new Board();

      newBoard.setNo(oldBoard.getNo());
      newBoard.setDate(new Date(System.currentTimeMillis()));
      newBoard.setTitle(
          prompt.inputString(String.format("내용(%s)? ", oldBoard.getTitle()), oldBoard.getTitle()));

      if (newBoard.getTitle().equals(oldBoard.getTitle())) {
        System.out.println("게시글 변경을 취소했습니다.");
        return;
      }
      
      boardDao.update(newBoard);

      System.out.println("게시글을 변경했습니다.");

    } catch (Exception e) {
      System.out.println("게시글 업데이트 중 오류발생!");
    }
  }
}


