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
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;

@WebServlet("/photoboard/detail")
public class PhotoBoardDetailServlet extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    try {
      ServletContext servletContext = req.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      int no = Integer.parseInt(req.getParameter("no"));
      PhotoBoard photoBoard;
      photoBoard = photoBoardService.get(no);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>사진 상세정보</title>");
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
      out.println("<a class='nav-item nav-link' href='../auth/loginForm'>로그인 <span class='sr-only'>(current)</span></a>");
      out.println("<a class='nav-item nav-link' href='../board/list'>게시글 목록 보기</a>");
      out.println("<a class='nav-item nav-link' href='../lesson/list'>수업목록 보기</a>");
      out.println("<a class='nav-item nav-link' href='../member/list'>멤버목록 보기</a>");
      out.println("</div>");
      out.println("</div>");
      out.println("</nav>");
      out.println("<h1>사진 상세정보</h1>");

      if (photoBoard != null) {
        out.println("<form action='update'>");
        out.printf("번호: <input name='no' type='text' readonly value='%d'><br>\n", //
            photoBoard.getNo());
        out.println("내용:<br>");
        out.printf("<textarea name='title' rows='5' cols='60'>%s</textarea><br>\n", //
            photoBoard.getTitle());
        out.printf("등록일: %s<br>\n", photoBoard.getCreatedDate());
        out.printf("조회수: %d<br>\n", photoBoard.getViewCount());
        out.printf("수업: %s<br>\n", photoBoard.getLesson().getTitle());
        out.println("<hr>");
        out.println("사진 파일:<br>");
        out.println("<ul>\n");
        for (PhotoFile photoFile : photoBoard.getFiles()) {
          out.printf("  <li>%s</li>\n", photoFile.getFilePath());
        }
        out.println("</ul>");

        out.println("사진: <input name='photo1' type='file'><br>");
        out.println("사진: <input name='photo2' type='file'><br>");
        out.println("사진: <input name='photo3' type='file'><br>");
        out.println("사진: <input name='photo4' type='file'><br>");
        out.println("사진: <input name='photo5' type='file'><br>");

        out.println("<p><button>변경</button>");
        out.printf("<a href='delete?no=%d&lessonNo=%d'>삭제</a></p>\n", //
            photoBoard.getNo(), //
            photoBoard.getLesson().getNo());
        out.println("</form>");

      } else {
        out.println("<p>해당 번호의 사진 게시글이 없습니다.</p>");
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
