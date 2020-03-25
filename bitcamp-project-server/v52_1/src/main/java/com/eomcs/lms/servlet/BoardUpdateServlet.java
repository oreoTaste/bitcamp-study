package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;
import com.eomcs.util.Prompt;
import com.eomcs.util.RequestMapping;

@Component
public class BoardUpdateServlet {

  BoardService boardService;

  public BoardUpdateServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/update")
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      
      int no = Prompt.getInt(in, out, "번호? ");

      Board oldBoard = boardService.get(no);
      if(oldBoard == null) {
        out.println("해당 번호의 게시글이 없습니다.");
        out.flush();
        return;
      }
      
      Board newBoard = new Board();

      newBoard.setTitle(
          Prompt.getString(
              in,
              out,
              String.format("제목(%s)", oldBoard.getTitle()),
              oldBoard.getTitle()));
      newBoard.setNo(oldBoard.getNo());
      newBoard.setDate(new Date(System.currentTimeMillis()));
      newBoard.setViewCount(0);
 
      if(boardService.update(newBoard)){
        out.println("게시글을 변경했습니다.");
        out.flush();
      } else {
        out.println("게시글 변경을 취소했습니다");
        out.flush();
      }

    } catch(Exception e) {
      
    }
  }
}