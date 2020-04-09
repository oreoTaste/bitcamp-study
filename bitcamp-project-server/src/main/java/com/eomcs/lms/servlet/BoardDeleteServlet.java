package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.service.BoardService;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      BoardService boardService = iocContainer.getBean(BoardService.class);

      int no = Integer.parseInt(request.getParameter("no"));
      if(boardService.delete(no)) {
        response.sendRedirect("list");
      } else
        throw new Exception("삭제할 게시물 정보가 유효하지 않습니다.");

    } catch (Exception e) {
      request.getSession().setAttribute("errorMsg", e);
      request.getSession().setAttribute("errorUrl", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }

  }


}
