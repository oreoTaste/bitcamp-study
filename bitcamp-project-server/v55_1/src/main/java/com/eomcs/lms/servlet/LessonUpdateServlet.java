package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;


@WebServlet("/lesson/update")
public class LessonUpdateServlet  extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    printHead(out);
    printHead2(out);
    

    printHead(out);

    ServletContext servletContext = request.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);

    out.println("수업정보 수정");
    
    int no = Integer.parseInt(request.getParameter("no"));
    Lesson lesson;
    try {
      lesson = lessonService.get(no);
    out.printf("<form action='update' method='post'>");
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      printHead(out);
      out.println("<meta http-equiv='refresh' content='2 url=list'>");
      printHead2(out);
      
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      LessonService lessonService = iocContainer.getBean(LessonService.class);


      out.println("<h1> 수업 정보 변경 </h1>");

      Lesson newLesson = new Lesson();

      newLesson.setNo(Integer.parseInt(request.getParameter("no")));
      newLesson.setTitle(request.getParameter("title"));
      newLesson.setContext(request.getParameter("context"));
      newLesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
      newLesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
      newLesson.setTotalHour(Integer.parseInt(request.getParameter("totalHour")));
      newLesson.setDailyHour(Integer.parseInt(request.getParameter("dailyHour")));

      lessonService.update(newLesson);
      out.println("수업을 변경했습니다.");

    } catch (Exception e) {
      out.println("수업정보 업데이트 중 오류발생");
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
  }

  private void printHead2(PrintWriter out) {
    out.println("<title>수업정보 수정</title>");
    out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh' crossorigin='anonymous'>");
    out.println("</head>");

    out.println("<body>");
    out.println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
    out.println("<a class='navbar-brand' href='../'>LMS 시스템</a>");
    out.println("<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNavAltMarkup' aria-controls='navbarNavAltMarkup' aria-expanded='false' aria-label='Toggle navigation'>");
    out.println("<span class='navbar-toggler-icon'></span>");
    out.println("</button>");
    out.println("<div class='collapse navbar-collapse' id='navbarNavAltMarkup'>");
    out.println("<div class='navbar-nav'>");
    out.println("<a class='nav-item nav-link' href='../auth/login'>로그인 <span class='sr-only'>(current)</span></a>");
    out.println("<a class='nav-item nav-link' href='../board/list'>게시글 목록 보기</a>");
    out.println("<a class='nav-item nav-link' href='../lesson/list'>수업목록 보기</a>");
    out.println("<a class='nav-item nav-link' href='../member/list'>멤버목록 보기</a>");
    out.println("</div>");
    out.println("</div>");
    out.println("</nav>");
    
  }

}

