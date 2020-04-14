package com.eomcs.lms.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.util.RequestMapping;

@Component
public class LessonDetailServlet  {
  @Autowired
  LessonService lessonService;
  
  @RequestMapping("/lesson/detail")
  protected String doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");

    Lesson lesson = null;
    try {
      lesson = lessonService.get(Integer.parseInt(request.getParameter("no")));
      request.setCharacterEncoding("UTF-8");
      request.setAttribute("lesson", lesson);
      return "/lesson/detail.jsp";
      
    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }
  }


}
