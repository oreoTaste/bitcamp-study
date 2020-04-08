package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.RequestMapping;

@Component
public class LessonUpdateFormServlet {
  LessonService lessonService;

  public LessonUpdateFormServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @RequestMapping("/lesson/updateForm")
  public void service(Map<String, String> map, PrintStream out) throws Exception {

    printHead(out);
    out.println("수업정보 수정");
    
    int no = Integer.parseInt(map.get("no"));
    Lesson lesson = lessonService.get(no);
    out.printf("<form action='/lesson/update'>");
    out.printf("수업번호: <input name='no' readonly value=%s><br>",lesson.getNo());
    out.printf("수업명: <input name='title' value=%s><br>",lesson.getTitle());
    out.printf("수업내용: <input name='context' value=%s><br>",lesson.getContext());
    out.printf("시작일: <input name='startDate' value=%s><br>",lesson.getStartDate());
    out.printf("종료일: <input name='endDate' value=%s><br>",lesson.getEndDate());
    out.printf("총수업시간: <input name='totalHour' value=%s><br>",lesson.getTotalHour());
    out.printf("일수업시간: <input name='dailyHour' value=%s><br>",lesson.getDailyHour());
    out.printf("<button>변경</button>");
    out.printf("</form>");

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
    out.println("<title>수업정보 수정</title>");
    out.println("</head>");

    out.println("<body>");
  }

}
