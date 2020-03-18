package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;
import com.eomcs.util.Prompt;

public class BoardAddServlet implements Servlet {
  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    Board board = new Board();
    
    board.setTitle(Prompt.getString(in, out, "제목? "));

    if (boardService.add(board)) { // 삭제했다면
      out.println("새 글을 등록했습니다.");

    } else {
      out.println("게시물 등록에 실패했습니다.");
    }
    
  }
}
