package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@WebServlet("/board/detail")
public class BoardDetailServlet  extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    try {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    printHead(out);

    ServletContext servletContext = req.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    BoardService boardService = iocContainer.getBean(BoardService.class);

    int no = Integer.parseInt(req.getParameter("no"));
    Board board;
      board = boardService.get(no);

    printHead(out);

    if(board != null) {
      out.printf("번호: %d<br>", board.getNo());
      out.printf("제목: %s<br>", board.getTitle());
      out.printf("등록일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS<br>", board.getDate());
      out.printf("조회수: %d<br>", board.getViewCount());
      out.printf("<div><a href='delete?no=%d'>삭제</a>", board.getNo());
      out.printf("  ..  ");
      out.printf("<a href='updateForm?no=%d'>변경</a>", board.getNo());
    } else {
      out.println("해당 번호의 게시물이 없습니다.<br>");
      out.flush();
    }
    out.println("  ..  ");
    out.println("<a href='list'>게시글 목록으로 돌아가기</a></div>");
    printTail(out);
    
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void printTail(PrintWriter out) {
    out.println("</body>");
    out.println("</html>");
  }

  private void printHead(PrintWriter out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글 상세정보</title>");
    out.println("</head>");
    out.println("<body>");
  }
}
