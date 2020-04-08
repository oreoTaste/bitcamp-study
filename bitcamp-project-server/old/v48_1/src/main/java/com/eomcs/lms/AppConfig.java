package com.eomcs.lms;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.sql.MybatisDaoFactory;
import com.eomcs.sql.PlatformTransactionManager;
import com.eomcs.sql.SqlSessionFactoryProxy;

@ComponentScan(value = "com.eomcs.lms")
public class AppConfig {
  
  // Spring IoC 컨테이너에 수동으로 등록할 개체는
  // 메서드를 만들어 리턴한다.
  @Bean
  public SqlSessionFactory SqlSessionFactory() throws Exception {
    // Mybatis용 입력스트림 준비
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/lms/conf/mybatis-config.xml");

    // Transaction제어를 위한 프록시객체
    return new SqlSessionFactoryProxy(
        new SqlSessionFactoryBuilder().build(inputStream));
  }
  
  @Bean
  public MybatisDaoFactory MybatisDaoFactory(SqlSessionFactory sqlSessionFactory) throws Exception {
    return new MybatisDaoFactory(sqlSessionFactory);
  }
  
  @Bean
  public BoardDao BoardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(BoardDao.class);
  }
  
  @Bean
  public LessonDao LessonDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(LessonDao.class);
  }

  @Bean
  public MemberDao MemberDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(MemberDao.class);
  }

  @Bean
  public PhotoBoardDao PhotoBoardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoBoardDao.class);
  }

  @Bean
  public PhotoFileDao PhotoFileDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoFileDao.class);
  }
  
  @Bean
  public PlatformTransactionManager TransactionManager(SqlSessionFactory sqlSessionFactory) {
    return new PlatformTransactionManager(sqlSessionFactory);
  }
  
}








