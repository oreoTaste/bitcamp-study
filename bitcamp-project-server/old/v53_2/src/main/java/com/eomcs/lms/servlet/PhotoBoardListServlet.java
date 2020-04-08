package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;
import com.eomcs.util.RequestMapping;

@Component
public class PhotoBoardListServlet {

  PhotoBoardService photoBoardService;
  LessonService lessonService;

  public PhotoBoardListServlet(//
      PhotoBoardService photoBoardService, //
      LessonService lessonService) {
    this.photoBoardService = photoBoardService;
    this.lessonService = lessonService;
  }

  @RequestMapping("/photoboard/list")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    try {
      int lessonNo = Integer.parseInt(params.get("lessonNo"));
      Lesson lesson = lessonService.get(lessonNo);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("  <meta charset='UTF-8'>");
      if (lesson == null)
        throw new Exception("수업 번호가 유효하지 않습니다.");
      List<PhotoBoard> photoBoards = photoBoardService.listLessonPhoto(lessonNo);
      if(photoBoards.isEmpty()) {
        out.printf("  <meta http-equiv='refresh' content=\"2; url='/lesson/detail?no=%d'\">", lessonNo);
      }
      out.println("  <title>강의 사진 목록</title>");
      out.println("</head>");
      out.println("<body>");

      out.printf("  <h1>강의 사진 : %s</h1>", lesson.getTitle());
      out.println("<hr>");
      if(photoBoards.isEmpty()) {
        out.printf("사진이 없습니다.<br> 수업정보로 돌아갑니다.<br>");
      } else {

        out.printf("  <a href='/photoboard/addForm?lessonNo=%d'>새 사진</a><br>\n", //
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
            + "<td><a href='/photoboard/detail?no=%d'>%s</a></td> "//
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
