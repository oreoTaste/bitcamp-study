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

@WebServlet("/lesson/add")
public class LessonAddServlet extends HttpServlet {
  private static final long serialVersionUID = 20200331;


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();


    printHead(out);
    printHead2(out);
    out.println("<h1>수업 입력</h1>");

    out.println("<form action='add' method='post'>");
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
    out.println("<button>등록</button>");
    out.println("</form>");

    printTail(out);
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {

      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      LessonService lessonService = iocContainer.getBean(LessonService.class);

      Lesson lesson = new Lesson();
      lesson.setTitle(request.getParameter("title"));
      lesson.setContext(request.getParameter("context"));
      lesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
      lesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
      lesson.setTotalHour(Integer.parseInt(request.getParameter("totalHour")));
      lesson.setDailyHour(Integer.parseInt(request.getParameter("dailyHour")));

      if (lessonService.add(lesson)){
        response.sendRedirect("list");
      } else
        throw new Exception("수업정보 입력에 실패했습니다.(중복값)");

    } catch (Exception e) {
//      throw new ServletException();
      request.getSession().setAttribute("errorMsg", e);
      request.getSession().setAttribute("errorUrl", "list");
      request.getRequestDispatcher("/error").forward(request, response);
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
  }

  private void printHead2(PrintWriter out) {
    out.println("<title> 수업 입력 </title>");
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
