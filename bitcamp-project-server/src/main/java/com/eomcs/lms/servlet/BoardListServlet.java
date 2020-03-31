package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@WebServlet("/board/list")
public class BoardListServlet extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    try {
      printHead(out);

      ServletContext servletContext = req.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      BoardService boardService = iocContainer.getBean(BoardService.class);

      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("<th>번호</th>");
      out.println("<th>제목</th>");
      out.println("<th>등록일</th>");
      out.println("<th>조회수</th>");
      out.println("</tr>");

      List<Board> boards = boardService.list();

      for(Board board : boards) {

        out.printf("<tr>");
        out.printf("<td><a href='detail?no=%d'>%d</a></td>", board.getNo(), board.getNo());
        out.printf("<td><a href='detail?no=%d'>%s</a></td>", board.getNo(), board.getTitle());
        out.printf("<td><a href='detail?no=%d'>%s</a></td>", board.getNo(), board.getDate());
        out.printf("<td><a href='detail?no=%d'>%d</a></td>", board.getNo(), board.getViewCount());
        out.printf("</tr>"); //
      }

      printTail(out);
    } catch(Exception e) {
      throw new ServletException();
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
    out.println("<title> 게시글 목록 </title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>게시글</h1>");
    out.println("<a href='addForm'>새글</a><br>");

  }
}















