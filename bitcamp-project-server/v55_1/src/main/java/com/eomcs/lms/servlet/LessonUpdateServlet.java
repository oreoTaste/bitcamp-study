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
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;


@WebServlet("/lesson/update")
public class LessonUpdateServlet  extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    request.getRequestDispatcher("/header").include(request, response);
    ServletContext servletContext = request.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);

    out.println("수업정보 수정");
    
    int no = Integer.parseInt(request.getParameter("no"));
    Lesson lesson;
    try {
      lesson = lessonService.get(no);
    out.printf("<form action='update' method='post'>");
    out.printf("수업번호: <input name='no' readonly value=%s><br>",lesson.getNo());
    out.printf("수업명: <input name='title' value=%s><br>",lesson.getTitle());
    out.printf("수업내용: <input name='context' value=%s><br>",lesson.getContext());
    out.printf("시작일: <input name='startDate' value=%s><br>",lesson.getStartDate());
    out.printf("종료일: <input name='endDate' value=%s><br>",lesson.getEndDate());
    out.printf("총수업시간: <input name='totalHour' value=%s><br>",lesson.getTotalHour());
    out.printf("일수업시간: <input name='dailyHour' value=%s><br>",lesson.getDailyHour());
    out.printf("<button>변경</button>");
    out.printf("</form>");

    request.getRequestDispatcher("/footer").include(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    try {
      
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      LessonService lessonService = iocContainer.getBean(LessonService.class);

      Lesson newLesson = new Lesson();

      newLesson.setNo(Integer.parseInt(request.getParameter("no")));
      newLesson.setTitle(request.getParameter("title"));
      newLesson.setContext(request.getParameter("context"));
      newLesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
      newLesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
      newLesson.setTotalHour(Integer.parseInt(request.getParameter("totalHour")));
      newLesson.setDailyHour(Integer.parseInt(request.getParameter("dailyHour")));

      if(lessonService.update(newLesson)) {
        response.sendRedirect("list");
      } else
        throw new Exception("수업정보 수정에 실패했습니다.");
    } catch (Exception e) {
      request.getSession().setAttribute("errorMsg", e);
      request.getSession().setAttribute("errorUrl", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }

  }


}

