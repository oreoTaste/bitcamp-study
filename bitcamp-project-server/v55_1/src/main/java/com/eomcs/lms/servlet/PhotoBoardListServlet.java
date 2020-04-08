package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;

@WebServlet("/photoboard/list")
public class PhotoBoardListServlet extends HttpServlet {
  private static final long serialVersionUID =20200331;


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);
      LessonService lessonService = iocContainer.getBean(LessonService.class);

      int lessonNo = Integer.parseInt(request.getParameter("lessonNo"));
      Lesson lesson = lessonService.get(lessonNo);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("  <meta charset='UTF-8'>");
      if (lesson == null)
        throw new Exception("수업 번호가 유효하지 않습니다.");
      List<PhotoBoard> photoBoards = photoBoardService.listLessonPhoto(lessonNo);
      if(photoBoards.isEmpty()) {
        out.printf("  <meta http-equiv='refresh' content=\"2; url='../lesson/detail?no=%d'\">", lessonNo);
      }
      out.println("  <title>강의 사진 목록</title>");
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

      out.printf("  <h1>강의 사진 : %s</h1>", lesson.getTitle());
      out.println("<hr>");
      if(photoBoards.isEmpty()) {
        out.printf("사진이 없습니다.<br> 수업정보로 돌아갑니다.<br>");
      } else {

        out.printf("  <a href='add?lessonNo=%d'>새 사진</a><br>\n", //
            lessonNo);
        out.println("  <table border='1'>");
        out.println("  <tr>");
        out.println("    <th>번호</th>");
        out.println("    <th>제목</th>");
        out.println("    <th>등록일</th>");
        out.println("    <th>조회수</th>");
        out.println("  </tr>");
      }

      for (PhotoBoard photoBoard : photoBoards) {
        out.printf("  <tr>"//
            + "<td>%d</td> "//
            + "<td><a href='detail?no=%d'>%s</a></td> "//
            + "<td>%s</td> "//
            + "<td>%d</td>"//
            + "</tr>\n", //
            photoBoard.getNo(), //
            photoBoard.getNo(), //
            photoBoard.getTitle(), //
            photoBoard.getCreatedDate(), //
            photoBoard.getViewCount() //
            );
      }
      out.println("</table>");

    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    out.println("</body>");
    out.println("</html>");
  }
}
