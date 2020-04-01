package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@WebServlet("/board/update")
public class BoardUpdateServlet extends GenericServlet {
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
      
      Board newBoard = new Board();
      int no = Integer.parseInt(req.getParameter("no"));
      String title = req.getParameter("title");

      newBoard.setTitle(title);
      newBoard.setNo(no);
      newBoard.setDate(new Date(System.currentTimeMillis()));
      newBoard.setViewCount(0);
 
      out.println("<h1>게시글 변경 결과</h1>");
      if(boardService.update(newBoard)){
        out.println("<p>게시글을 변경했습니다.</p>");
      } else {
        out.println("<p>게시글 변경을 취소했습니다</p>");
      }
    } catch(Exception e) {
      
    }
    
    printTail(out);
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
    out.println("<title> 게시글 수정 </title>");
    out.println("</head>");

    out.println("<body>");
  }
}