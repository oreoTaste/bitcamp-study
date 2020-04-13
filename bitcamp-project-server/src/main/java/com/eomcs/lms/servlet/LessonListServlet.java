package com.eomcs.lms.servlet;

import java.io.IOException;
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

@WebServlet("/lesson/list")
public class LessonListServlet extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
//    PrintWriter out = response.getWriter();

//    request.getRequestDispatcher("/header").include(request, response);
    ServletContext servletContext = request.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);

//    out.println("<h1>수업목록</h1>");
//
//    out.println("<div><form action='search'>");
//    out.println("<input type='text' size='80' name='keyword' value='수업명을 입력해주세요'>");
//    out.println("<button>수업목록</button></div>");
//    out.printf("<a href='add' method='get'>신규수업</a>");
//    out.printf("<table border='1'");
//    out.printf("<tr>");
//    out.printf("<th>레슨번호</th>");
//    out.printf("<th>수업명</th>");
//    out.printf("<th>수업내용</th>");
//    out.printf("<th>시작일</th>");
//    out.printf("<th>종료일</th>");
//    out.printf("<th>총시간</th>");
//    out.printf("<th>일시간</th>");
//    out.printf("</tr>");

    List<Lesson> lesson;
    try {
      lesson = lessonService.list();
      request.setAttribute("lesson", lesson);
      request.getRequestDispatcher("/lesson/list.jsp").include(request, response);
//
//      for (Lesson ls : lesson) {
//        out.printf("<tr>");
//        out.printf("<td><a href='detail?no=%d'>%d</a></td>", ls.getNo(), ls.getNo());
//        out.printf("<td><a href='detail?no=%d'>%s</a></td>", ls.getNo(), ls.getTitle());
//        out.printf("<td><a href='detail?no=%d'>%s</a></td>", ls.getNo(), ls.getContext());
//        out.printf("<td><a href='detail?no=%d'>%tF</a></td>", ls.getNo(), ls.getStartDate());
//        out.printf("<td><a href='detail?no=%d'>%tF</a></td>", ls.getNo(), ls.getEndDate());
//        out.printf("<td><a href='detail?no=%d'>%d</a></td>", ls.getNo(), ls.getTotalHour());
//        out.printf("<td><a href='detail?no=%d'>%d</a></td>", ls.getNo(), ls.getDailyHour());
//        out.printf("</tr>");
//      }
//
//      request.getRequestDispatcher("/footer").include(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



}
