package com.eomcs.lms.handler;

import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.prompt.Prompt;

// "/board/detail" 명령 처리
public class BoardDetailCommand implements Command {

  BoardDao boardDao;
  Prompt prompt;

  public BoardDetailCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Board board = boardDao.findByNo(no);

      System.out.printf("번호: %d\n", board.getNo());
      System.out.printf("제목: %s\n", board.getTitle());
      System.out.printf("등록일: %s\n", board.getDate());
      System.out.printf("조회수: %d\n", board.getViewCount());

    } catch (Exception e) {
      System.out.println("게시판 상세정보 열람중 오류발생!");
    }
  }
}


