package com.eomcs.lms;

import java.io.InputStream;
import java.util.HashMap;
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
import com.eomcs.sql.MybatisDaoFactory;
import com.eomcs.sql.PlatformTransactionManager;
import com.eomcs.sql.SqlSessionFactoryProxy;
import com.eomcs.util.ApplicationContext;

public class ContextLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      // ApplicationContext에서 자동으로 생성하지 못하는 객체는
      // 따로 생성하여, 맵에 보관한다.
      HashMap<String, Object> beans = new HashMap<>();

      // Mybatis용 입력스트림 준비
      InputStream inputStream = Resources.getResourceAsStream(
          "com/eomcs/lms/conf/mybatis-config.xml");
      
      // Transaction제어를 위한 프록시객체
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(inputStream));
      beans.put("sqlSessionFactory", sqlSessionFactory);
      
      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      beans.put("transactionManager", txManager);

      // Dao 객체
      MybatisDaoFactory mybatisDaoFactory = new MybatisDaoFactory(sqlSessionFactory);
      beans.put("lessonDao", mybatisDaoFactory.createDao(LessonDao.class));
      beans.put("boardDao", mybatisDaoFactory.createDao(BoardDao.class));
      beans.put("memberDao", mybatisDaoFactory.createDao(MemberDao.class));
      beans.put("photoBoardDao", mybatisDaoFactory.createDao(PhotoBoardDao.class));
      beans.put("photoFileDao", mybatisDaoFactory.createDao(PhotoFileDao.class));
      
      // IoC 컨테이너 준비
      ApplicationContext appCtx = new ApplicationContext("com.eomcs.lms", beans);
      context.put("iocContainer", appCtx);
      appCtx.printBeans();
      
    } catch(Exception e) {

    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
  }



}
