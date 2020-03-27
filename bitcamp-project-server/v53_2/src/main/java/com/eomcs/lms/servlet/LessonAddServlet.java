package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.RequestMapping;

@Component
public class LessonAddServlet {

  LessonService lessonService;

  public LessonAddServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @RequestMapping("/lesson/add")
  public void service(Map<String, String> map, PrintStream out) throws Exception {
    try {
      printHead(out);
      out.println("<h1>수업 입력</h1>");
      
      Lesson lesson = new Lesson();
      lesson.setTitle(map.get("title"));
      lesson.setContext(map.get("context"));
      lesson.setStartDate(Date.valueOf(map.get("startDate")));
      lesson.setEndDate(Date.valueOf(map.get("endDate")));
      lesson.setTotalHour(Integer.parseInt(map.get("totalHour")));
      lesson.setDailyHour(Integer.parseInt(map.get("dailyHour")));

      if (lessonService.add(lesson)){
        out.println("새 글을 등록했습니다.");

      } else {
        out.println("수업정보 등록에 실패했습니다.");
      }

    } catch (Exception e) {
      System.out.println("수업정도 추가중 오류발생");
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
    out.println("<meta http-equiv='refresh' content='2; url=/lesson/list'>");
    out.println("<title> 수업 입력 </title>");
    out.println("</head>");

    out.println("<body>");
  }
  
}
