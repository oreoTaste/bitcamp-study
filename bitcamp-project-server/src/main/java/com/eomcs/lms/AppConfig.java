package com.eomcs.lms;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
// Spring IoC 컨테이너가 이 클래스를 Java Config로 자동인식 하려면
// 다음 태그를 붙여야한다. 단, 이 클래스가 @ComponentScan에서 지정한
// 패키지 안에 있어야한다.
// @Configuration
@ComponentScan(value="com.eomcs.lms")
public class AppConfig {
  static Logger logger = LogManager.getLogger(AppConfig.class);

  public AppConfig() {
    logger.debug("App Config 객체 생성!");
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








