package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;
import com.eomcs.util.Component;
import com.eomcs.util.Prompt;

@Component("/photoboard/list")
public class PhotoBoardListServlet implements Servlet {
  PhotoBoardService photoBoardService;
  LessonService lessonService;
  
  public PhotoBoardListServlet(PhotoBoardService photoBoardService, LessonService lessonService) {
    this.photoBoardService = photoBoardService;
    this.lessonService = lessonService;
  }
  
  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    // 1. 수업번호 가져오기
    int lessonNo = Prompt.getInt(in, out, "수업번호? ");

    Lesson lesson = lessonService.get(lessonNo);
    // 2. 수업번호에 해당하는 수업명이 없다면,
    // 안내문구를 출력하고 응답종료.
    if(lesson == null) {
      out.println("수업번호가 유효하지 않습니다.");
      return;
    }
    
    // 3. 수업번호에 해당하하는 수업명을 출력한다.
    out.printf("수업명 : %s\n", lesson.getTitle());
    out.flush();
    
    // 4. 해당 수업의 사진 게시글을 가져온다.
    List<PhotoBoard> photoBoards = photoBoardService.listLessonPhoto(lessonNo);
        
    out.println("-------------------------------------");
    for(PhotoBoard photoBoard : photoBoards) {
      out.printf("\t%d, \t%-10s, %tF, %d\n", //
          photoBoard.getNo(), //
          photoBoard.getTitle(), //
          photoBoard.getCreatedDate(), //
          photoBoard.getViewCount() //
      );
    }
  }

}
