package com.eomcs.lms;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoBoardDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoFileDaoImpl;
import com.eomcs.lms.service.impl.BoardServiceImpl;
import com.eomcs.lms.service.impl.LessonServiceImpl;
import com.eomcs.lms.service.impl.MemberServiceImpl;
import com.eomcs.lms.service.impl.PhotoBoardServiceImpl;
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
      
      LessonDao lessonDao = new LessonDaoImpl(sqlSessionFactory);
      BoardDao boardDao = new BoardDaoImpl(sqlSessionFactory);
      MemberDao memberDao = new MemberDaoImpl(sqlSessionFactory);
      PhotoBoardDao photoBoardDao = new PhotoBoardDaoImpl(sqlSessionFactory);
      PhotoFileDao photoFileDao = new PhotoFileDaoImpl(sqlSessionFactory);
      
      // ServerApp에서 SqlSession객체를 닫기위해 context에 넣어준다.
      context.put("boardService", new BoardServiceImpl(boardDao));
      context.put("lessonService", new LessonServiceImpl(lessonDao));
      context.put("memberService", new MemberServiceImpl(memberDao));
      context.put("photoBoardService", new PhotoBoardServiceImpl(
          txManager, photoBoardDao, photoFileDao));

      context.put("sqlSessionFactory", sqlSessionFactory);
      

    } catch(Exception e) {

    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
  }



}
