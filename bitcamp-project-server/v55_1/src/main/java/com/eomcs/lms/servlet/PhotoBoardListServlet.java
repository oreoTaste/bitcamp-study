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


      if (lesson == null)
        throw new Exception("수업 번호가 유효하지 않습니다.");
      List<PhotoBoard> photoBoards = photoBoardService.listLessonPhoto(lessonNo);
      if(photoBoards.isEmpty()) {
        request.setAttribute("refreshUrl", String.format("../lesson/detail?no=%d", lessonNo));
        request.getRequestDispatcher("/header").include(request, response);
      } else {
        request.getRequestDispatcher("/header").include(request, response);
      }

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
    request.getRequestDispatcher("/footer").include(request, response);
  }
}
