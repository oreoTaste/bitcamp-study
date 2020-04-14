package com.eomcs.lms.servlet;

import java.io.IOException;
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
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;

@WebServlet("/photoboard/update")
@MultipartConfig(maxFileSize = 5000000)
public class PhotoBoardUpdateServlet extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    ServletContext servletContext = request.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);
    try {

      int no = Integer.parseInt(request.getParameter("no"));
      PhotoBoard photoBoard;
      photoBoard = photoBoardService.get(no);
      photoBoard.setTitle(request.getParameter("title"));

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
      
      if (photoFiles.size() > 0) {
        photoBoard.setFiles(photoFiles);
      } else {
        photoBoard.setFiles(null);
      }

      try {
        if(photoBoardService.update(photoBoard)) {
          response.sendRedirect(String.format("list?lessonNo=%d",photoBoard.getLesson().getNo()));
        } else
          throw new Exception("사진게시물 수정이 불가합니다.");
      } catch (Exception e) {
        throw e;
      }

    } catch (Exception e1) {
      request.setAttribute("errorMsg", e1);
      request.setAttribute("errorUrl", "list");
      request.getRequestDispatcher("/error").forward(request, response);

    }
  }
}
