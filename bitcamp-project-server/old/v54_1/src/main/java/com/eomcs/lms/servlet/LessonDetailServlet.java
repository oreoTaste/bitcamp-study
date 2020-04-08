package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@WebServlet("/lesson/detail")
public class LessonDetailServlet  extends GenericServlet {
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
    Lesson lesson = null;
    try {
      lesson = lessonService.get(Integer.parseInt(req.getParameter("no")));

      out.printf("번호? %d<br>", lesson.getNo());
      out.printf("수업명: %s<br>", lesson.getTitle());
      out.printf("수업내용: %s<br>", lesson.getContext());
      out.printf("기간 : %tF ~ %tF<br>", lesson.getStartDate(), lesson.getEndDate());
      out.printf("일수업시간: %d<br>", lesson.getTotalHour());
      out.printf("일수업시간: %d<br>", lesson.getDailyHour());
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("수업정보 세부사항 수신 중 오류발생");
    }



    out.printf("<button type='button' onclick=location.href='../photoboard/list?lessonNo=%d'>사진게시판</button>  ..  ", lesson.getNo());

    out.printf("<button type='button' onclick=location.href='delete?no=%d'>삭제</button>  ..  ", lesson.getNo());
    out.printf("<button type='button' onclick=location.href='updateForm?no=%d'>변경</button>  ..  ", lesson.getNo());
    out.printf("<button type='button' onclick=location.href='list'>수업 목록으로 돌아가기</button>");
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
    out.println("<title> 수업 목록 </title>");
    out.println("</head>");

    out.println("<body>");
  }

}
