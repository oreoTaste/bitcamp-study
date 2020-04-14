package com.eomcs.lms.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.util.RequestMapping;

@Component
public class LessonListServlet {

  @Autowired
  LessonService lessonService;

  @RequestMapping("/lesson/list")
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    List<Lesson> lesson = lessonService.list();
    request.setAttribute("lesson", lesson);
    response.setContentType("text/html;charset=UTF-8");
    return "/lesson/list.jsp";
  }



}
