package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;


@WebServlet("/lesson/search")
public class LessonSearchServlet  extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    printHead(out);

    ServletContext servletContext = req.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);

    printHead(out);
    out.println("<h1> 수업 정보 검색 </h1>");

    String keyword = req.getParameter("keyword").toString();
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

    List<Lesson> lesson;
    try {
      lesson = lessonService.search(searchMap);
    } catch (Exception e) {
      throw new ServletException();
    }

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
        out.printf("<td><a href='detail?no=%d'>%d</a></td>", ls.getNo(), ls.getNo());
        out.printf("<td><a href='detail?no=%d'>%s</a></td>", ls.getNo(), ls.getTitle());
        out.printf("<td><a href='detail?no=%d'>%s</a></td>", ls.getNo(), ls.getContext());
        out.printf("<td><a href='detail?no=%d'>%tF</a></td>", ls.getNo(), ls.getStartDate());
        out.printf("<td><a href='detail?no=%d'>%tF</a></td>", ls.getNo(), ls.getEndDate());
        out.printf("<td><a href='detail?no=%d'>%d</a></td>", ls.getNo(), ls.getTotalHour());
        out.printf("<td><a href='detail?no=%d'>%d</a></td>", ls.getNo(), ls.getDailyHour());
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
    out.println("<title> 수업 정보 검색 </title>");
    out.println("</head>");

    out.println("<body>");
  }

}
