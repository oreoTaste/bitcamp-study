package com.eomcs.lms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.RequestMapping;

@Component
public class LessonDeleteServlet {

  LessonService lessonService;

  public LessonDeleteServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @RequestMapping("/lesson/delete")
  public void service(Map<String, String> map, PrintWriter out) throws Exception {
    
    printHead(out);
    out.println("<h1> 수업 삭제 </h1>");

    try {
      int no = Integer.parseInt(map.get("no"));
      
      if(lessonService.delete(no)) {
        out.println("수업을 삭제했습니다.");
      } else {
        out.println("해당 번호의 수업이 없습니다");
      }

    } catch (Exception e) {
      out.println("수업정보 삭제중 오류발생!");
    }
    
    printTail(out);
  }
  
  private void printTail(PrintWriter out) {
    out.println("</body>");
    out.println("</html>");
  }

  private void printHead(PrintWriter out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2; url=/lesson/list'>");
    out.println("<title> 수업 삭제 </title>");
    out.println("</head>");

    out.println("<body>");
  }
  
}
