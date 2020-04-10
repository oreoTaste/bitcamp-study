package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@WebServlet("/lesson/detail")
public class LessonDetailServlet  extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    request.getRequestDispatcher("/header").include(request, response);
    out.println("<h1>수업 세부정보</h1>");

    ServletContext servletContext = request.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);

    Lesson lesson = null;
    try {
      lesson = lessonService.get(Integer.parseInt(request.getParameter("no")));

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
    out.printf("<button type='button' onclick=location.href='update?no=%d'>변경</button>  ..  ", lesson.getNo());
    out.printf("<button type='button' onclick=location.href='list'>수업 목록으로 돌아가기</button>");
    request.getRequestDispatcher("/footer").include(request, response);
  }


}
