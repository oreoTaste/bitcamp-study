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
import com.eomcs.lms.service.LessonService;

@WebServlet("/lesson/delete")
public class LessonDeleteServlet extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    printHead(out);

    ServletContext servletContext = req.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);

    printHead(out);
    out.println("<h1> 수업 삭제 </h1>");

    try {
      int no = Integer.parseInt(req.getParameter("no"));

      if(lessonService.delete(no)) {
        out.println("수업을 삭제했습니다.");
      } else {
        out.println("해당 번호의 수업이 없습니다");
      }

    } catch (Exception e) {
      out.println("수업정보 삭제중 오류발생!");
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
    out.println("<meta http-equiv='refresh' content='2; url=list'>");
    out.println("<title> 수업 삭제 </title>");
    out.println("</head>");

    out.println("<body>");
  }

}
