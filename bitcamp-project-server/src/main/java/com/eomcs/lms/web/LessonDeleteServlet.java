package com.eomcs.lms.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.service.LessonService;

@WebServlet("/lesson/delete")
public class LessonDeleteServlet extends HttpServlet {
  private static final long serialVersionUID =20200331;


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");

    ServletContext servletContext = request.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      if(lessonService.delete(no)) {
        request.setAttribute("viewUrl", "redirect:list");
      } else
        throw new Exception("수업정보 삭제에 실패했습니다.");

    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      request.setAttribute("errorUrl", "list");
    }

  }


}
