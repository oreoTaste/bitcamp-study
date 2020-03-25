package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.RequestMapping;

@Component
public class LessonSearchServlet {

  LessonService lessonService;
  
  public LessonSearchServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }
  
  @RequestMapping("/lesson/search")
  public void service(HashMap<String, Object> map, PrintStream out) throws Exception {

    printHead(out);
    out.println("<h1> 수업 정보 검색 </h1>");
    
    String keyword = (String) map.get("keyword");
    if(keyword.length() > 0) {
      map.put("title", keyword);
      map.put("context", keyword);
      map.put("startDate", keyword);
      map.put("endDate", keyword);
      map.put("totalHour", keyword);
      map.put("dailyHour", keyword);
    }
    
    List<Lesson> lesson = lessonService.search(map);
    
    out.println("------------------");
    out.println("검색결과 :");
    for (Lesson ls : lesson) {
      out.printf("%d, \t%-8s, \t%s, %tF ~ %tF, %d, %d<br>",
      ls.getNo(),
      ls.getTitle(),
      ls.getContext(),
      ls.getStartDate(),
      ls.getEndDate(),
      ls.getTotalHour(),
      ls.getDailyHour());
    }
    printTail(out);

  }
  
  private void printTail(PrintStream out) {
    out.println("</body>");
    out.println("</html>");
  }

  private void printHead(PrintStream out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
//    out.println("<meta http-equiv='refresh' content='2 url=/lesson/list'>");
    out.println("<title> 수업 정보 검색 </title>");
    out.println("</head>");

    out.println("<body>");
  }

}
