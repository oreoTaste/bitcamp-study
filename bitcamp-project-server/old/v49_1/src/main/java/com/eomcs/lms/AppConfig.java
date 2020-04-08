package com.eomcs.lms;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@ComponentScan(value="com.eomcs.lms")
@PropertySource("classpath:com/eomcs/lms/conf/jdbc.properties")
@MapperScan("com.eomcs.lms.dao")
public class AppConfig {

  // @PropertySource로 로딩한 .properties파일의 값 사용하기
  @Value("${jdbc.driver}")
  String jdbcDriver;

  @Value("${jdbc.url}")
  String jdbcUrl;

  @Value("${jdbc.username}")
  String jdbcUsername;

  @Value("${jdbc.password}")
  String jdbcPassword;

  // Spring IoC 컨테이너에서 사용할 Properties 파일을 로딩하기
  @Bean
  public DataSource DataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(jdbcUrl);
    ds.setUsername(jdbcUsername);
    ds.setPassword(jdbcPassword);
    return ds;
  }

  // Spring IoC 컨테이너에 수동으로 등록할 개체는
  // 메서드를 만들어 리턴한다.
  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource ds, ApplicationContext appCtx) throws Exception {
     
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

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
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








