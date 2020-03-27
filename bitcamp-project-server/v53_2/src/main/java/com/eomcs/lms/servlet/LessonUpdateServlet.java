package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.RequestMapping;

@Component
public class LessonUpdateServlet {

  LessonService lessonService;

  public LessonUpdateServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @RequestMapping("/lesson/update")
  public void service(Map<String, String> map, PrintStream out) throws Exception {

    try {
      printHead(out);
      out.println("<h1> 수업 정보 변경 </h1>");

      Lesson newLesson = new Lesson();

      newLesson.setNo(Integer.parseInt(map.get("no")));
      newLesson.setTitle(map.get("title"));
      newLesson.setContext(map.get("context"));
      newLesson.setStartDate(Date.valueOf(map.get("startDate")));
      newLesson.setEndDate(Date.valueOf(map.get("endDate")));
      newLesson.setTotalHour(Integer.parseInt(map.get("totalHour")));
      newLesson.setDailyHour(Integer.parseInt(map.get("dailyHour")));
      
      lessonService.update(newLesson);
        out.println("수업을 변경했습니다.");
        
    } catch (Exception e) {
      out.println("수업정보 업데이트 중 오류발생");
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
    out.println("<meta http-equiv='refresh' content='2 url=/lesson/list'>");
    out.println("<title> 수업 정보 변경 </title>");
    out.println("</head>");

    out.println("<body>");
  }
  
}
