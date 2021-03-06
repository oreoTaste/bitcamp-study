package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.service.BoardService;
import com.eomcs.util.Prompt;
import com.eomcs.util.RequestMapping;

@Component
public class BoardDeleteServlet {

  BoardService boardService;

  public BoardDeleteServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/delete")
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      int no = Prompt.getInt(in, out, "번호?");

      if(boardService.delete(no)) {
        out.println("게시글을 삭제했습니다.");
      } else {
        out.println("해당 번호의 게시글이 없습니다.");
      }

    } catch (Exception e) {
      out.println("게시판 정보 삭제 중 오류발생!");
    }

  }
}
