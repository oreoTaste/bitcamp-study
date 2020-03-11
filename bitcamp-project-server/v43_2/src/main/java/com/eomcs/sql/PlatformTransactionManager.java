package com.eomcs.sql;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PlatformTransactionManager {
  
  SqlSessionFactory sqlSessionFactory;
 
  public PlatformTransactionManager(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }
  
  public void beginTransaction() throws Exception {
    // 기존 스레드에 존재하는 sqlSession객체를 지운다.
    ((SqlSessionFactoryProxy)sqlSessionFactory).closeSession();
    
    sqlSessionFactory.openSession(false);
  }

  public void commit() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    
    sqlSession.commit();
    
  }

  public void rollback() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    
    sqlSession.rollback();
  }

}
