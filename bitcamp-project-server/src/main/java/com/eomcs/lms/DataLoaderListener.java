package com.eomcs.lms;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoBoardDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoFileDaoImpl;
import com.eomcs.sql.DataSource;
import com.eomcs.sql.PlatformTransactionManager;

public class DataLoaderListener implements ApplicationContextListener {
  String jdbcUrl = "jdbc:mariadb://localhost:3306/studydb";
  String username = "study";
  String password = "1111";

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      DataSource dataSource = new DataSource(jdbcUrl, username, password);
      PlatformTransactionManager txManager = new PlatformTransactionManager(dataSource);

      context.put("dataSource", dataSource);
      context.put("transactionManager", txManager);

      InputStream inputStream = Resources.getResourceAsStream(
          "com/eomcs/lms/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      
      // 이 메서드를 호출한 쪽(App)에서 Dao객체를 사용할 수 있도록 Map에 담아둔다.
      context.put("boardDao", new BoardDaoImpl(sqlSessionFactory));
      context.put("lessonDao", new LessonDaoImpl(sqlSessionFactory));
      context.put("memberDao", new MemberDaoImpl(sqlSessionFactory));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(sqlSessionFactory));
      context.put("photoFileDao", new PhotoFileDaoImpl(sqlSessionFactory));

    } catch(Exception e) {

    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    DataSource dataSource = (DataSource) context.get("dataSource");
    dataSource.clean();
  }



}
