package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Connection;
import java.util.Scanner;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.util.ConnectionFactory;
import com.eomcs.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

  ConnectionFactory conFactory;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(ConnectionFactory conFactory,
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.conFactory = conFactory;
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Connection con = conFactory.getConnection();
    con.setAutoCommit(false);

    try {
      int no = Prompt.getInt(in, out, "사진 번호?");

      photoFileDao.deleteAll(no);
      
      if(photoBoardDao.delete(no) > 0) {
        out.println("사진 게시글을 삭제했습니다.");
        con.commit();
      } else
        throw new Exception("해당 번호의 사진 게시글이 없습니다.");

    } catch (Exception e) {
      con.rollback();
      out.println(e.getMessage());
    } finally {
      con.setAutoCommit(true);
    }
    
  }
}
