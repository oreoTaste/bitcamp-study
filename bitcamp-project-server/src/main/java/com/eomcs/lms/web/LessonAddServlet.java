package com.eomcs.lms.web;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.util.RequestMapping;

@Component
public class LessonAddServlet {

  @Autowired
  LessonService lessonService;

  @RequestMapping("/lesson/add")
  protected String doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html;charset=UTF-8");
    if(request.getMethod().equals("GET"))
      return "/lesson/form.jsp";
    try {

      Lesson lesson = new Lesson();
      lesson.setTitle(request.getParameter("title"));
      lesson.setContext(request.getParameter("context"));
      lesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
      lesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
      lesson.setTotalHour(Integer.parseInt(request.getParameter("totalHour")));
      lesson.setDailyHour(Integer.parseInt(request.getParameter("dailyHour")));

      if (lessonService.add(lesson))
        return "redirect:list";
      else
        throw new Exception("수업정보 입력에 실패했습니다.(중복값)");

    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }

  }



}
