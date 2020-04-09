package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@WebServlet("/board/detail")
public class BoardDetailServlet  extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    try {
    PrintWriter out = response.getWriter();

    ServletContext servletContext = getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    BoardService boardService = iocContainer.getBean(BoardService.class);

    request.getRequestDispatcher("/header").include(request, response);
    out.println("<h1>게시글 세부정보</h1>");
    int no = Integer.parseInt(request.getParameter("no"));
    Board board;
      board = boardService.get(no);

    if(board != null) {
      out.printf("번호: %d<br>", board.getNo());
      out.printf("제목: %s<br>", board.getTitle());
      out.printf("등록일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS<br>", board.getDate());
      out.printf("조회수: %d<br>", board.getViewCount());
      out.printf("<div><a href='delete?no=%d'>삭제</a>", board.getNo());
      out.printf("  ..  ");
      out.printf("<a href='update?no=%d'>변경</a>", board.getNo());
    } else {
      out.println("해당 번호의 게시물이 없습니다.<br>");
      out.flush();
    }
    out.println("  ..  ");
    out.println("<a href='list'>게시글 목록으로 돌아가기</a></div>");
    request.getRequestDispatcher("/footer").include(request, response);
    
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
