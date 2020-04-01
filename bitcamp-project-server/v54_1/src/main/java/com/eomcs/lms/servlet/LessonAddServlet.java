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
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@WebServlet("/lesson/add")
public class LessonAddServlet extends GenericServlet {
  private static final long serialVersionUID = 20200331;


  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    try {
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();

      ServletContext servletContext = req.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      LessonService lessonService = iocContainer.getBean(LessonService.class);
      printHead(out);
      out.println("<h1>수업 입력</h1>");

      Lesson lesson = new Lesson();
      lesson.setTitle(req.getParameter("title"));
      lesson.setContext(req.getParameter("context"));
      lesson.setStartDate(Date.valueOf(req.getParameter("startDate")));
      lesson.setEndDate(Date.valueOf(req.getParameter("endDate")));
      lesson.setTotalHour(Integer.parseInt(req.getParameter("totalHour")));
      lesson.setDailyHour(Integer.parseInt(req.getParameter("dailyHour")));

      if (lessonService.add(lesson)){
        out.println("새 글을 등록했습니다.");

      } else {
        out.println("수업정보 등록에 실패했습니다.");
      }
      printTail(out);

    } catch (Exception e) {
      System.out.println("수업정도 추가중 오류발생");
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
    out.println("<meta http-equiv='refresh' content='2; url=list'>");
    out.println("<title> 수업 입력 </title>");
    out.println("</head>");

    out.println("<body>");
  }

}
