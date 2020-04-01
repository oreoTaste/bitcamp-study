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

@WebServlet("/board/add")
public class BoardAddServlet extends GenericServlet {
  private static final long serialVersionUID = 20200331;


  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    
    try {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    ServletContext servletContext = req.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    BoardService boardService = iocContainer.getBean(BoardService.class);
    
    printHead(out);
    
    Board board = new Board();
    String title = req.getParameter("title");
    board.setTitle(title);

    out.println("<h1>입력결과</h1>");
      if (boardService.add(board)) { // 삭제했다면
        out.println("<p>새 글을 등록했습니다.</p>");
      } else {
        out.println("<p>게시물 등록에 실패했습니다.</p>");
      }
    
    printTail(out);
    
    } catch (Exception e) {
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
    out.println("<meta http-equiv=\"refresh\" content='2; url=list'>");
    out.println("<title> 게시글 목록 </title>");
    out.println("</head>");

    out.println("<body>");
  }
  
}
