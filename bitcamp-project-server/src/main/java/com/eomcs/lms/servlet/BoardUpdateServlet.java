package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
  private static final long serialVersionUID =20200331;
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
      request.setCharacterEncoding("UTF-8");
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      BoardService boardService = iocContainer.getBean(BoardService.class);

      request.getRequestDispatcher("/header").include(request, response);
      out.println("<h1>게시글 수정</h1>");

      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardService.get(no);
      if(board == null) {
        out.println("<p>해당 번호의 게시글이 없습니다.</p>");
      } else {

        out.println("<form action='update' method='post'>");
        out.printf("번호:<input name='no' type='text' value='%d' readonly><br>\n", board.getNo());
        out.println("내용:<br>");
        out.printf("<textarea name='title' rows='5' cols='60'>%s</textarea><br>",board.getTitle());
        out.printf("등록일: %s<br>\n", board.getDate());
        out.printf("조회수: %s<br>\n", board.getViewCount());
        out.println("<button>수정하기</button>");
        out.println("</form>");

      }
      request.getRequestDispatcher("/footer").include(request, response);
    } catch(Exception e) {
      throw new ServletException();
    }
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");

    try {

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      BoardService boardService = iocContainer.getBean(BoardService.class);
      
      Board newBoard = new Board();
      int no = Integer.parseInt(request.getParameter("no"));
      String title = request.getParameter("title");

      newBoard.setTitle(title);
      newBoard.setNo(no);
      newBoard.setDate(new Date(System.currentTimeMillis()));
      newBoard.setViewCount(0);
 
      if(boardService.update(newBoard)) {
        response.sendRedirect("list");
      } else
        throw new Exception("수정할 게시물 정보가 유효하지 않습니다.");
        
    } catch(Exception e) {
      request.getSession().setAttribute("errorMsg", e);
      request.getSession().setAttribute("errorUrl", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
    
  }
  
}