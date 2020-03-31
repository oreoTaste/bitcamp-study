package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/lesson/addForm")
public class LessonAddFormServlet extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();


    printHead(out);
    out.println("<h1>수업 입력</h1>");
    
    out.println("<form action='add'>");
    out.println("제목");
    out.println("<textarea name='title' rows='1' cols='63'></textarea><br>");
    out.println("내용");
    out.println("<textarea name='context' rows='1' cols='63'></textarea><br>");
    out.println("시작일");
    out.println("<input type='date' name='startDate'>");
    out.println("    종료일");
    out.println("<input type='date' name='endDate'><br>");
    out.println("총수업시간");
    out.println("<input type='number' name='totalHour'>");
    out.println("일수업시간");
    out.println("<input type='number' name='dailyHour'><br>");
    out.println("<button>제출하기</button>");
    out.println("</form>");
    
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
    out.println("<title> 수업 입력 </title>");
    out.println("</head>");

    out.println("<body>");
  }
  
}
