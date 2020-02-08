package com.eomcs.lms.handler;

import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.prompt.Prompt;

// "/board/delete" 명령 처리
public class BoardDeleteCommand implements Command {

  BoardDao boardDao;
  Prompt prompt;

  public BoardDeleteCommand(BoardDao boardDao , Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      boardDao.delete(no);
      System.out.println("게시글을 삭제했습니다.");

    } catch (Exception e) {
      System.out.println("게시판 정보 삭제 중 오류발생!");
    }
  }
}


