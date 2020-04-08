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
import com.eomcs.sql.PlatformTransactionManager;
import com.eomcs.sql.SqlSessionFactoryProxy;

public class DataLoaderListener implements ApplicationContextListener {
  String jdbcUrl = "jdbc:mariadb://localhost:3306/studydb";
  String username = "study";
  String password = "1111";

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      InputStream inputStream = Resources.getResourceAsStream(
          "com/eomcs/lms/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(inputStream));
      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      
      // ServerApp에서 SqlSession객체를 닫기위해 context에 넣어준다.
      context.put("sqlSessionFacoty", sqlSessionFactory);
      context.put("transactionManager", txManager);
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
  }



}
