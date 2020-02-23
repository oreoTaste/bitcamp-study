package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;

public class BoardDeleteServlet implements Servlet {

  BoardDao boardDao;

  public BoardDeleteServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      out.println("번호?\n!{}!");
      out.flush();
      int no = Integer.parseInt(in.nextLine());

      if(boardDao.delete(no) > 0) {
        System.out.println("게시글을 삭제했습니다.");
      } else {
        System.out.println("해당 번호의 게시글이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("게시판 정보 삭제 중 오류발생!");
    }
    
  }
}
