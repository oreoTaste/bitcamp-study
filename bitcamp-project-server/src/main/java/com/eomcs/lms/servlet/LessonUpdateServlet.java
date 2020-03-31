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


@WebServlet("/lesson/update")
public class LessonUpdateServlet  extends GenericServlet {
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
      LessonService lessonService = iocContainer.getBean(LessonService.class);


      out.println("<h1> 수업 정보 변경 </h1>");

      Lesson newLesson = new Lesson();

      newLesson.setNo(Integer.parseInt(req.getParameter("no")));
      newLesson.setTitle(req.getParameter("title"));
      newLesson.setContext(req.getParameter("context"));
      newLesson.setStartDate(Date.valueOf(req.getParameter("startDate")));
      newLesson.setEndDate(Date.valueOf(req.getParameter("endDate")));
      newLesson.setTotalHour(Integer.parseInt(req.getParameter("totalHour")));
      newLesson.setDailyHour(Integer.parseInt(req.getParameter("dailyHour")));

      lessonService.update(newLesson);
      out.println("수업을 변경했습니다.");

    } catch (Exception e) {
      out.println("수업정보 업데이트 중 오류발생");
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
    out.println("<meta http-equiv='refresh' content='2 url=list'>");
    out.println("<title> 수업 정보 변경 </title>");
    out.println("</head>");

    out.println("<body>");
  }

}

