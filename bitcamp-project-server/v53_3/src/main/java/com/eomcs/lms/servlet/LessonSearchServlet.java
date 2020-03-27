package com.eomcs.lms.servlet;

import java.io.PrintWriter;
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
  public void service(HashMap<String, Object> map, PrintWriter out) throws Exception {

    printHead(out);
    out.println("<h1> 수업 정보 검색 </h1>");

    String keyword = map.get("keyword").toString();
    HashMap<String, Object> searchMap = new HashMap<>();
    if(keyword.length() > 0) {
      searchMap.put("title", keyword);
      searchMap.put("context", keyword);
      //      searchMap.put("startDate", keyword);
      //      searchMap.put("endDate", keyword);
      //      searchMap.put("totalHour", keyword);
      //      searchMap.put("dailyHour", keyword);
      out.printf("입력값 : %s<br>", keyword);
      out.println("------------------------------------<br>");
    }

    List<Lesson> lesson = lessonService.search(searchMap);

    out.println("검색결과 : <br>");

    
    if(!lesson.isEmpty()) {
      out.printf("<table border='1'");
      out.printf("<tr>");
      out.printf("<th>레슨번호</th>");
      out.printf("<th>수업명</th>");
      out.printf("<th>수업내용</th>");
      out.printf("<th>시작일</th>");
      out.printf("<th>종료일</th>");
      out.printf("<th>총시간</th>");
      out.printf("<th>일시간</th>");
      out.printf("</tr>");
      
      for (Lesson ls : lesson) {
        out.printf("<tr>");
        out.printf("<td><a href='/lesson/detail?no=%d'>%d</a></td>", ls.getNo(), ls.getNo());
        out.printf("<td><a href='/lesson/detail?no=%d'>%s</a></td>", ls.getNo(), ls.getTitle());
        out.printf("<td><a href='/lesson/detail?no=%d'>%s</a></td>", ls.getNo(), ls.getContext());
        out.printf("<td><a href='/lesson/detail?no=%d'>%tF</a></td>", ls.getNo(), ls.getStartDate());
        out.printf("<td><a href='/lesson/detail?no=%d'>%tF</a></td>", ls.getNo(), ls.getEndDate());
        out.printf("<td><a href='/lesson/detail?no=%d'>%d</a></td>", ls.getNo(), ls.getTotalHour());
        out.printf("<td><a href='/lesson/detail?no=%d'>%d</a></td>", ls.getNo(), ls.getDailyHour());
        out.printf("</tr>");
      }
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
    //    out.println("<meta http-equiv='refresh' content='2 url=/lesson/list'>");
    out.println("<title> 수업 정보 검색 </title>");
    out.println("</head>");

    out.println("<body>");
  }

}
