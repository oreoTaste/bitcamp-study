package com.eomcs.lms;

import javax.sql.DataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.eomcs.lms.dao")
public class MybatisConfig {
  static Logger logger = LogManager.getLogger(AppConfig.class);

  public MybatisConfig() {
    logger.debug("Mybatis Config 객체 생성!");
  }

  
  // Spring IoC 컨테이너에 수동으로 등록할 개체는
  // 메서드를 만들어 리턴한다.
  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource ds, ApplicationContext appCtx) throws Exception {
    // Mybatis의 log4j 활성화시키기;
    LogFactory.useLog4JLogging();
    
    //mybatis측에서 제작한 Spring ios용 factory
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(ds);
    // TypeAliases 위치정보를 넘겨준다.
    sqlSessionFactoryBean.setTypeAliasesPackage("com.eomcs.lms.domain");
    // Mapper 파일의 위치정보를 넘겨준다.
    sqlSessionFactoryBean.setMapperLocations(
        appCtx.getResources("classpath:com/eomcs/lms/mapper/*Mapper.xml"));
    
    return sqlSessionFactoryBean.getObject();
  }

  /*
  @Bean
  public BoardDao boardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(BoardDao.class);
  }

  @Bean
  public LessonDao lessonDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(LessonDao.class);
  }

  @Bean
  public MemberDao memberDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(MemberDao.class);
  }

  @Bean
  public PhotoBoardDao photoBoardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoBoardDao.class);
  }

  @Bean
  public PhotoFileDao photoFileDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoFileDao.class);
  }
  */

}








