package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDetailServlet implements Servlet {

  BoardDao boardDao;

  public BoardDetailServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Board board= boardDao.findByNo(no);

    if(board != null) {
      out.printf("번호: %d\n", board.getNo());
      out.printf("제목: %s\n", board.getTitle());
      out.printf("등록일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getDate());
      out.printf("조회수: %d\n", board.getViewCount());
    } else {
      out.println("해당 번호의 게시물이 없습니다.");
      out.flush();

    }
  }
}