package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@WebServlet("/lesson/detail")
public class LessonDetailServlet  extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");

    ServletContext servletContext = request.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);

    Lesson lesson = null;
    try {
      lesson = lessonService.get(Integer.parseInt(request.getParameter("no")));
      request.setCharacterEncoding("UTF-8");
      request.setAttribute("lesson", lesson);
      request.setAttribute("viewUrl", "/lesson/detail.jsp");
      
    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      request.setAttribute("errorUrl", "list");
    }
  }


}
