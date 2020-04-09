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

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {
  private static final long serialVersionUID = 20200331;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    request.getRequestDispatcher("/header").include(request, response);
    out.println("<h1>게시물입력</h1>");
    out.println("<form action='add' method='post'>");
    out.println("내용:<br>");
    out.println("<textarea name='title' rows='5' cols='60'></textarea><br>");
    out.println("<button>등록</button>");
    out.println("</form>");

    request.getRequestDispatcher("/footer").include(request, response);
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      request.setCharacterEncoding("UTF-8");
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      BoardService boardService = iocContainer.getBean(BoardService.class);

      Board board = new Board();
      board.setTitle(request.getParameter("title"));
      if(boardService.add(board)) {
        response.sendRedirect("list");
      } else
        throw new Exception("추가할 게시물 정보가 유효하지 않습니다.");

    } catch (Exception e) {
      request.getSession().setAttribute("errorMsg", e);
      request.getSession().setAttribute("errorUrl", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }



}
