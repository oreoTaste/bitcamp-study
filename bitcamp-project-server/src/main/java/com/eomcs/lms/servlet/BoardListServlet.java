package com.eomcs.lms.servlet;

import java.io.IOException;
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

    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      BoardService boardService = iocContainer.getBean(BoardService.class);
      

      List<Board> boards = boardService.list();
      
      // JSP에게 출력할 객체를 전달
      request.setAttribute("list", boards);

      // JSP를 include하기
      // 인클루드 하는 쪽에서 출력스트림의 콘텐트 타입은 필수!
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/board/list.jsp").include(request, response);
      
    } catch(Exception e) {
      throw new ServletException();
    }
  }

}















