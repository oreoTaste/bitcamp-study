package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;

@WebServlet("/photoboard/add")
@MultipartConfig(maxRequestSize = 5000000)
public class PhotoBoardAddServlet extends HttpServlet {
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
      LessonService lessonService = iocContainer.getBean(LessonService.class);

      int lessonNo = Integer.parseInt(request.getParameter("lessonNo"));
      Lesson lesson;
      lesson = lessonService.get(lessonNo);

      request.getRequestDispatcher("/header").include(request, response);

      out.println("<h1>사진 입력</h1>");
      out.println("<form action='add' method='post' enctype='multipart/form-data'>");
      out.printf("강의번호: <input name='lessonNo' type='text' value='%d' readonly><br>\n", //
          lesson.getNo());
      out.printf("강의명: %s<br>\n", lesson.getTitle());
      out.println("내용:<br>");
      out.println("<textarea name='title' rows='5' cols='60'></textarea><br>");
      out.println("<hr>");
      out.println("사진: <input name='photo' type='file'><br>");
      out.println("사진: <input name='photo' type='file'><br>");
      out.println("사진: <input name='photo' type='file'><br>");
      out.println("사진: <input name='photo' type='file'><br>");
      out.println("사진: <input name='photo' type='file'><br>");
      out.println("<button>제출</button>");
      out.println("</form>");

      request.getRequestDispatcher("/footer").include(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    ServletContext servletContext = request.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    LessonService lessonService = iocContainer.getBean(LessonService.class);
    PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

    int lessonNo = Integer.parseInt(request.getParameter("lessonNo"));

    try {
      Lesson lesson = lessonService.get(lessonNo);
      if (lesson == null)
        throw new Exception("수업 번호가 유효하지 않습니다.");

      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(request.getParameter("title"));
      photoBoard.setLesson(lesson);

      ArrayList<PhotoFile> photoFiles = new ArrayList<>();


      Collection<Part> parts = request.getParts();
      for (Part part : parts) {
        if(!part.getName().equals("photo") || part.getSize()<=0) {
          continue;
        }

        if(part.getSize() > 0) {
          String dirPath = getServletContext().getRealPath("/upload/photoboard");
          String filename = UUID.randomUUID().toString();

          part.write(dirPath + "/" + filename); // 여기서 문제 발생
          photoFiles.add(new PhotoFile().setFilePath(filename));
        }
      }

      if (photoFiles.size() == 0)
        throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");

      photoBoard.setFiles(photoFiles);
      if(photoBoardService.add(photoBoard)) {
        response.sendRedirect("../lesson/list");
      } else
        throw new Exception("사진게시물 등록이 불가합니다.(중복값 발생)");

    } catch (Exception e) {
      request.getSession().setAttribute("errorMsg", e);
      request.getSession().setAttribute("errorUrl","list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
