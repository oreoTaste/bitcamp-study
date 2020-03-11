package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.BoardDao;

public class BoardListServlet implements Servlet {

  BoardDao boardDao;

  public BoardListServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");

    out.reset();
    // => 기존에 출력했던 List<Board> 객체의 직렬화 데이터를 무시하고
    // 새로 직렬화를 수행한다.
 
    out.writeObject(boardDao.findAll());
  }

}
