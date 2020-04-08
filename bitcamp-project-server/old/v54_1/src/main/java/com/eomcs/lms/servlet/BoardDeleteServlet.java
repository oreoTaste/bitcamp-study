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
import com.eomcs.lms.service.BoardService;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends GenericServlet {
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

      out.println("<h1>게시글 삭제 결과</h1>");
      int no = Integer.parseInt(req.getParameter("no"));
      if(boardService.delete(no)) {
        out.println("<p>게시글을 삭제했습니다.</p>");
      } else {
        out.println("<p>해당 번호의 게시글이 없습니다.</p>");
      }

    } catch (Exception e) {
      out.println("<p>게시판 정보 삭제 중 오류발생!</p>");
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
    out.println("<title> 게시글 삭제 </title>");
    out.println("</head>");

    out.println("<body>");
  }

}
