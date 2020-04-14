package com.eomcs.lms.web;

import java.io.IOException;
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

//  @Override
//  protected void doGet(HttpServletRequest request, HttpServletResponse response)
//      throws ServletException, IOException {
//
//    request.setCharacterEncoding("UTF-8");
//    response.setContentType("text/html;charset=UTF-8");
//    ServletContext servletContext = request.getServletContext();
//    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
//    LessonService lessonService = iocContainer.getBean(LessonService.class);
//
//
//    int no = Integer.parseInt(request.getParameter("no"));
//    Lesson lesson;
//    try {
//      lesson = lessonService.get(no);
//
//    } catch (Exception e) {
//      request.setAttribute("errorMsg", e);
//      request.setAttribute("errorUrl", "list");
//      request.getRequestDispatcher("/error").forward(request, response);
//    }
//  }
  
  
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
        
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("viewUrl", "redirect:list");
      } else
        throw new Exception("수업정보 수정에 실패했습니다.");
    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      request.setAttribute("errorUrl", "list");
    }

  }


}

