package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardListServlet implements Servlet {

  BoardDao boardDao;

  public BoardListServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    List<Board> boards = boardDao.findAll();

    // Application Server 아키텍쳐에서는
    // 클라이언트가 출력할 내용을 서버가 보내주는 것이 핵심이다.
    // -> 추후 변경사항이 발생하면, 서버쪽만 출력내용을 바꾸면 된다.
    
    for(Board board : boards) {
      out.printf("\t%d, \t%-10s, %tF, %d\n", //
          board.getNo(), //
          board.getTitle(), //
          board.getDate(), //
          board.getViewCount() //
      );
    }
  }

}
