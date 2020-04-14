package com.eomcs.lms.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;


@WebServlet("/lesson/search")
public class LessonSearchServlet  extends HttpServlet {
  private static final long serialVersionUID =20200331;


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      LessonService lessonService = iocContainer.getBean(LessonService.class);


      String keyword = request.getParameter("keyword").toString();
      HashMap<String, Object> searchMap = new HashMap<>();
      if(keyword.length() > 0) {
        searchMap.put("title", keyword);
        searchMap.put("context", keyword);
        //      searchMap.put("startDate", keyword);
        //      searchMap.put("endDate", keyword);
        //      searchMap.put("totalHour", keyword);
        //      searchMap.put("dailyHour", keyword);
      }

      List<Lesson> lesson;
      try {
        lesson = lessonService.search(searchMap);
      } catch (Exception e) {
        throw new ServletException();
      }

      if(!lesson.isEmpty()) {
        request.setAttribute("keyword", keyword);
        request.setAttribute("lesson", lesson);
        request.setAttribute("viewUrl", "/lesson/search.jsp");
      }
    } catch(Exception e) {
      request.setAttribute("errorMsg", e);
      request.setAttribute("errorUrl", "list");
    }

  }


}
