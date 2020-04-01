package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

      printHead(out);

      ServletContext servletContext = req.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      LessonService lessonService = iocContainer.getBean(LessonService.class);
    
    printHead(out);
    
    out.println("<h1>수업목록</h1>");
    
    out.println("<div><form action='search'>");
    out.println("<input type='text' size='80' name='keyword' value='수업명을 입력해주세요'>");
    out.println("<button>수업목록</button></div>");
    out.printf("<button type='button' onclick=\"location.href='addForm'\">신규수업</button>");
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

    List<Lesson> lesson;
    try {
      lesson = lessonService.list();
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
    
    printTail(out);
    } catch (Exception e) {
      e.printStackTrace();
    }
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
    out.println("<title> 수업 목록 </title>");
    out.println("</head>");

    out.println("<body>");
  }
  
  
}
