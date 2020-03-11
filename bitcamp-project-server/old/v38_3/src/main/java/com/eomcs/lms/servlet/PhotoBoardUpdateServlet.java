package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Prompt;

public class PhotoBoardUpdateServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
 
  public PhotoBoardUpdateServlet() {}
  
  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      int no = Prompt.getInt(in, out, "번호? ");

      PhotoBoard photoBoard = photoBoardDao.findByNo(no);
      if(photoBoard == null) {
        out.println("해당 번호의 사진 게시글이 없습니다.");
        out.flush();
        return;
      }
      
      PhotoBoard newPhotoBoard = new PhotoBoard();
      newPhotoBoard.setTitle(
          Prompt.getString(in, out, String.format("제목(%s)\n", photoBoard.getTitle())));
      newPhotoBoard.setNo(photoBoard.getNo());
      newPhotoBoard.setCreatedDate(new Date(System.currentTimeMillis()));
      newPhotoBoard.setViewCount(0);

      if(photoBoardDao.update(newPhotoBoard) > 0) {
        
        printPhotoFiles(in, out, no);
        
        out.println();
        out.println("사진은 일부만 변경할 수 없습니다.");
        out.println("전체를 새로 등록해야 합니다.");
        String response = Prompt.getString(in, out, "사진을 변경하시겠습니까?(y/N))");
        if(response.equalsIgnoreCase("y")) {
          
          photoFileDao.deleteAll(no);
          
          List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
          
          for(PhotoFile photoFile : photoFiles) {
            photoFile.setBoardNo(no);
            photoFileDao.insert(photoFile);
          }
          
        }

        out.println("사진 게시글을 변경했습니다.");
        out.flush();
      } else {
        out.println("사진 게시글 변경을 취소했습니다");
        out.flush();
      }

    } catch(Exception e) {
      
    }
  }
  
  private void printPhotoFiles(Scanner in, PrintStream out, int boardNo) throws Exception {

    out.println("사진파일 : ");
    List<PhotoFile> oldPhotoFiles = photoFileDao.findAll(boardNo);
    
    for(PhotoFile photoFile : oldPhotoFiles) {
      out.printf("> %s\n", photoFile.getFilePath());
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