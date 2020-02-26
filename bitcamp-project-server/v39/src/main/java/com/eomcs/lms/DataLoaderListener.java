package com.eomcs.lms;

import java.util.Map;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoBoardDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoFileDaoImpl;
import com.eomcs.util.ConnectionFactory;

public class DataLoaderListener implements ApplicationContextListener {
  String jdbcUrl = "jdbc:mariadb://localhost:3306/studydb";
  String username = "study";
  String password = "1111";
  
  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      ConnectionFactory conFactory = new ConnectionFactory(jdbcUrl, username, password);
      
      
    // 이 메서드를 호출한 쪽(App)에서 Dao객체를 사용할 수 있도록 Map에 담아둔다.
    context.put("boardDao", new BoardDaoImpl(conFactory));
    context.put("lessonDao", new LessonDaoImpl(conFactory));
    context.put("memberDao", new MemberDaoImpl(conFactory));
    context.put("photoBoardDao", new PhotoBoardDaoImpl(conFactory));
    context.put("photoFileDao", new PhotoFileDaoImpl(conFactory));
     
    } catch(Exception e) {
      
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

  }
  


}
