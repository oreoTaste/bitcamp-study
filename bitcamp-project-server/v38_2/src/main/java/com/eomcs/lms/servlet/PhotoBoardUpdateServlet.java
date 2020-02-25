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
      out.println("번호? \n!{}!");
      out.flush();
      int no = Integer.parseInt(in.nextLine());

      PhotoBoard photoBoard = photoBoardDao.findByNo(no);
      if(photoBoard == null) {
        out.println("해당 번호의 사진 게시글이 없습니다.");
        out.flush();
        return;
      }
      out.printf("제목(%s)\n", photoBoard.getTitle());
      out.println("!{}!");
      out.flush();
      
      PhotoBoard newPhotoBoard = new PhotoBoard();

      newPhotoBoard.setNo(photoBoard.getNo());
      newPhotoBoard.setCreatedDate(new Date(System.currentTimeMillis()));
      newPhotoBoard.setTitle(in.nextLine());
      newPhotoBoard.setViewCount(0);

      if(photoBoardDao.update(newPhotoBoard) > 0) {
        
        out.println("사진파일 : ");
        List<PhotoFile> oldPhotoFiles = photoFileDao.findAll(no);
        
        for(PhotoFile photoFile : oldPhotoFiles) {
          out.printf("> %s\n", photoFile.getFilePath());
        }
        out.println();
        
        out.println("사진은 일부만 변경할 수 없습니다.");
        out.println("전체를 새로 등록해야 합니다.");
        out.println("사진을 변경하시겠습니까?(y/N)\n!{}!");
        out.flush();
        
        String response = in.nextLine();
        if(response.equalsIgnoreCase("y")) {
          
          photoFileDao.deleteAll(no);
          
          ArrayList<PhotoFile> photoFiles = new ArrayList<>();
          
          while(true) {
            out.println("사진 파일?\n!{}!");
            out.flush();
            String filePath = in.nextLine();
            
            if(filePath.length() == 0) {
              if(photoFiles.size() == 0) {
                out.println("최소 하나이상의 사진파일을 등록해야 합니다.");
                continue;
              }
              break;
            }
            
            photoFiles.add(new PhotoFile()
                .setFilePath(filePath)
                .setBoardNo(photoBoard.getNo())
                );
          }
          for(PhotoFile photoFile : photoFiles) {
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
}