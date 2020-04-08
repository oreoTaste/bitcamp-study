package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;

@WebServlet("/photoboard/update")
public class PhotoBoardUpdateServlet extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    ServletContext servletContext = request.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);
    try {

      int no = Integer.parseInt(request.getParameter("no"));
      PhotoBoard photoBoard;
      photoBoard = photoBoardService.get(no);
      photoBoard.setTitle(request.getParameter("title"));

      ArrayList<PhotoFile> photoFiles = new ArrayList<>();
      for (int i = 1; i <= 5; i++) {
        String filepath = request.getParameter("photo" + i);
        if (filepath.length() > 0) {
          photoFiles.add(new PhotoFile().setFilePath(filepath));
        }
      }

      if (photoFiles.size() > 0) {
        photoBoard.setFiles(photoFiles);
      } else {
        photoBoard.setFiles(null);
      }

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.printf("<meta http-equiv='refresh' content='2;url=list?lessonNo=%d'>", //
          photoBoard.getLesson().getNo());
      out.println("<title>사진 변경</title>");
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

      out.println("<h1>사진 변경 결과</h1>");

      try {
        photoBoardService.update(photoBoard);
        out.println("<p>사진을 변경했습니다.</p>");
      } catch (Exception e) {
        out.println("<p>해당 사진 게시물이 존재하지 않습니다.</p>");
      }

      out.println("</body>");
      out.println("</html>");
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }
}
