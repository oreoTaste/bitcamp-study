package com.eomcs.lms.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@Controller
public class LessonController {

  @Autowired
  LessonService lessonService;

  @RequestMapping("/lesson/addForm")
  public String addForm() throws Exception {
    return "/lesson/form.jsp";
  }

  @RequestMapping("/lesson/add")
  public String add(Lesson lesson) throws Exception {
    if (lessonService.add(lesson))
      return "redirect:list";
    else
      throw new Exception("수업정보 입력에 실패했습니다.(중복값)");
  }

  @RequestMapping("/lesson/delete")
  public String delete(int no) throws Exception {
    if(lessonService.delete(no))
      return "redirect:list";
    else
      throw new Exception("수업정보 삭제에 실패했습니다.");
  }

  @RequestMapping("/lesson/detail")
  public String detail(int no, Map<String, Object> model) throws Exception {
    model.put("lesson", lessonService.get(no));
    return "/lesson/detail.jsp";
  }

  @RequestMapping("/lesson/list")
  public String list(Map<String, Object> model) throws Exception {
    model.put("lesson", lessonService.list());
    return "/lesson/list.jsp";
  }

  @RequestMapping("/lesson/search")
  public String search(Map<String, Object> model, Lesson lesson) throws Exception {

      HashMap<String, Object> searchMap = new HashMap<>();
      if(lesson.getTitle().length() > 0) {
        searchMap.put("title", lesson.getTitle().toString());
      }
      
      if(lesson.getStartDate()!=null) {
      searchMap.put("startDate", lesson.getStartDate().toString());
      }
      
      if(lesson.getEndDate()!=null) {
      searchMap.put("endDate", lesson.getEndDate().toString());
      }

      if(lesson.getTotalHour() > 0) {
      searchMap.put("totalHour", lesson.getTotalHour());
      }

      if(lesson.getDailyHour() > 0) {
      searchMap.put("dailyHour", lesson.getDailyHour());
      }
      
      model.put("lesson", lessonService.search(searchMap));
      return "/lesson/search.jsp";
  }

  @RequestMapping("/lesson/update")
  protected String update(Lesson lesson) throws Exception {
    if(lessonService.update(lesson))
      return "redirect:list";
    else
      throw new Exception("수업정보 수정에 실패했습니다.");

  }



}
