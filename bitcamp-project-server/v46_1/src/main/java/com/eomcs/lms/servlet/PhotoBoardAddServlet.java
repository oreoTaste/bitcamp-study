package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;
import com.eomcs.util.Component;
import com.eomcs.util.Prompt;

@Component("/photoboard/add")
public class PhotoBoardAddServlet implements Servlet {
  PhotoBoardService photoBoardService;
  LessonService lessonService;


  public PhotoBoardAddServlet(PhotoBoardService photoBoardService, LessonService lessonService) {
    this.photoBoardService = photoBoardService;
    this.lessonService = lessonService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();

    photoBoard.setTitle(
        Prompt.getString(in, out, "제목? "));
    int lessonNo =
        Prompt.getInt(in, out, "수업번호? ");
 
    Lesson lesson = lessonService.get(lessonNo);
    if(lesson == null) {
      out.println("수업번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setLesson(lesson);

    // ArrayList에 들어있는 photoFile데이터를 lms_photo_file데이터에 저장한다
    List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
    photoBoard.setFiles(photoFiles);
    try {
      photoBoardService.add(photoBoard);
      out.println("새 사진 게시글을 등록했습니다.");
    } catch(Exception e) {
      out.println(e.getMessage());
    }

  }

  private List<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {

    // 첨부파일 입력받기
    out.println("최소 하나이상의 사진파일을 등록해야 합니다.");
    out.println("파일명 입력없이 엔터를 치면 파일 추가를 마칩니다.");
    ArrayList<PhotoFile> photoFiles = new ArrayList<>();

    while(true) {
      String filePath = Prompt.getString(in, out, "사진 파일? ");

      if(filePath.length() == 0) {
        if(photoFiles.size() == 0) {
          out.println("최소 하나이상의 사진파일을 등록해야 합니다.");
          continue;
        }
        break;
      }

      //      기본 생성자를 사용할때
      //      PhotoFile photoFile = new PhotoFile();
      //      photoFile.setFilePath(filePath);
      //      photoFile.setBoardNo(photoBoard.getNo());

      //    초기값을 설정하는 생성자를 사용할때
      //
      //      PhotoFile photoFile = new PhotoFile(filePath, photoBoard.getNo());
      //      photoFiles.add(photoFile);

      //      세터메서드를 이용하여 체인방식으로 인스턴스 필드값을 설정
      photoFiles.add(new PhotoFile().setFilePath(filePath));

    }
    return photoFiles;
  }

}
