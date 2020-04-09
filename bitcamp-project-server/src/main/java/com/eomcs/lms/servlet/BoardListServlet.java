package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
      request.getRequestDispatcher("/header").include(request, response);
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      BoardService boardService = iocContainer.getBean(BoardService.class);
      
      out.println("<h1>게시글</h1>");
      out.println("<a href='add'>새글</a><br>");

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

      request.getRequestDispatcher("/footer").include(request, response);
    } catch(Exception e) {
      throw new ServletException();
    }
  }

}















