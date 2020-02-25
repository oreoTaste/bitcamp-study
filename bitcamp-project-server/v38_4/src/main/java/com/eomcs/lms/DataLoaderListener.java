package com.eomcs.lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoBoardDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoFileDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {
  public static Connection con;
  
  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
    // DB 연결 객체 준비
    Class.forName("org.mariadb.jdbc.Driver");
    con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
    
    // 이 메서드를 호출한 쪽(App)에서 Dao객체를 사용할 수 있도록 Map에 담아둔다.
    context.put("boardDao", new BoardDaoImpl(con));
    context.put("lessonDao", new LessonDaoImpl(con));
    context.put("memberDao", new MemberDaoImpl(con));
    context.put("photoBoardDao", new PhotoBoardDaoImpl(con));
    context.put("photoFileDao", new PhotoFileDaoImpl(con));
    
    } catch(Exception e) {
      
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    try {
      con.close();
    } catch(Exception e) {
      
    }

  }
  


}
