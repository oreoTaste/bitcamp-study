package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;


@WebServlet("/lesson/search")
public class LessonSearchServlet  extends HttpServlet {
  private static final long serialVersionUID =20200331;

  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    request.getRequestDispatcher("/header").include(request, response);
    ServletContext servletContext = request.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);

    out.println("<h1> 수업 정보 검색 </h1>");

    String keyword = request.getParameter("keyword").toString();
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

    request.getRequestDispatcher("/footer").include(request, response);
  }


}
