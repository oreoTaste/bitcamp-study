package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.sql.PlatformTransactionManager;
import com.eomcs.sql.TransactionCallback;
import com.eomcs.sql.TransactionTemplate;
import com.eomcs.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {
  TransactionTemplate transactionTemplate;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(PlatformTransactionManager txManager,
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    transactionTemplate = new TransactionTemplate(txManager);
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    transactionTemplate.execute(new TransactionCallback() {

      @Override
      public Object doinTransaction() throws Exception {
        int no = Prompt.getInt(in, out, "사진 번호?");
        photoFileDao.deleteAll(no);
        if(photoBoardDao.delete(no) > 0) {
          out.println("사진 게시글을 삭제했습니다.");
        } else
          throw new Exception("해당 번호의 사진 게시글이 없습니다.");
        return null;
      }

    });

  }
}
