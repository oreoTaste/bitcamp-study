package com.eomcs.lms.web;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.util.RequestMapping;

@Component
public class LessonController {

  @Autowired
  LessonService lessonService;

  @RequestMapping("/lesson/add")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

  @RequestMapping("/lesson/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html;charset=UTF-8");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      if(lessonService.delete(no))
        return "redirect:list";
      else
        throw new Exception("수업정보 삭제에 실패했습니다.");

    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }
  }

  @RequestMapping("/lesson/detail")
  public String detail(HttpServletRequest request, HttpServletResponse response)
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

  @RequestMapping("/lesson/list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {

    List<Lesson> lesson = lessonService.list();
    request.setAttribute("lesson", lesson);
    response.setContentType("text/html;charset=UTF-8");
    return "/lesson/list.jsp";
  }

  public String search(HttpServletRequest request, HttpServletResponse response) {

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

      request.setAttribute("keyword", keyword);
      request.setAttribute("lesson", lesson);
      return "/lesson/search.jsp";
    } catch(Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }
  }

  protected String update(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html;charset=UTF-8");
    try {
      Lesson newLesson = new Lesson();

      newLesson.setNo(Integer.parseInt(request.getParameter("no")));
      newLesson.setTitle(request.getParameter("title"));
      newLesson.setContext(request.getParameter("context"));
      newLesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
      newLesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
      newLesson.setTotalHour(Integer.parseInt(request.getParameter("totalHour")));
      newLesson.setDailyHour(Integer.parseInt(request.getParameter("dailyHour")));

      if(lessonService.update(newLesson))
        return "redirect:list";
      else
        throw new Exception("수업정보 수정에 실패했습니다.");
    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }

  }



}
