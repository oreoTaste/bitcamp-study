package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardUpdateServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
 
  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    try {
      out.println("번호? \n!{}!");
      out.flush();
      int no = Integer.parseInt(in.nextLine());

      PhotoBoard oldPhotoBoard = photoBoardDao.findByNo(no);
      if(oldPhotoBoard == null) {
        out.println("해당 번호의 사진 게시글이 없습니다.");
        out.flush();
        return;
      }
      out.printf("제목(%s)\n", oldPhotoBoard.getTitle());
      out.println("!{}!");
      out.flush();
      
      PhotoBoard newPhotoBoard = new PhotoBoard();

      newPhotoBoard.setNo(oldPhotoBoard.getNo());
      newPhotoBoard.setCreatedDate(new Date(System.currentTimeMillis()));
      newPhotoBoard.setTitle(in.nextLine());
      newPhotoBoard.setViewCount(0);

      if(photoBoardDao.update(newPhotoBoard) > 0) {
        out.println("사진 게시글을 변경했습니다.");
        out.flush();
      } else {
        out.println("사진 게시글 변경을 취소했습니다");
        out.flush();
      }

    } catch(Exception e) {
      
    }
  }
}