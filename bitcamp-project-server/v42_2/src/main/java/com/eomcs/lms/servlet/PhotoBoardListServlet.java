package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.Prompt;

public class PhotoBoardListServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  LessonDao lessonDao;

  public PhotoBoardListServlet(PhotoBoardDao photoBoardDao, LessonDao lessonDao) {
    this.photoBoardDao = photoBoardDao;
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    
    // 1. 수업번호 가져오기
    int lessonNo = Prompt.getInt(in, out, "수업번호? ");

    Lesson lesson = lessonDao.findByNo(lessonNo);
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
    List<PhotoBoard> photoBoards = photoBoardDao.findAllByLessonNo(lessonNo);
    
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
