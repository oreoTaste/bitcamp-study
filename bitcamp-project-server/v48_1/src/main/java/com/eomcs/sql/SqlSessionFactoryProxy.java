package com.eomcs.sql;

import java.sql.Connection;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class SqlSessionFactoryProxy implements SqlSessionFactory {

  SqlSessionFactory originalFactory;

  public SqlSessionFactoryProxy(SqlSessionFactory originalFactory) {
    this.originalFactory = originalFactory;
  }

  // SqlSession을 스레드에 보관할 저장소를 준비한다.
  ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

  public void close() {
    
  }
  
  @Override
  public SqlSession openSession() {
    return this.openSession(true);
  }

  @Override
  public SqlSession openSession(boolean autoCommit) {
    
    SqlSession sqlSession = threadLocal.get();
    
    if(sqlSession == null) {
      sqlSession = new SqlSessionProxy(originalFactory.openSession(autoCommit));
      threadLocal.set(sqlSession);
    }
    return sqlSession;
  }
  
  public void closeSession() {
    SqlSession sqlSession = threadLocal.get();
    if(sqlSession != null) {
      threadLocal.remove();
      ((SqlSessionProxy)sqlSession).realClose();
    }
  }

  @Override
  public SqlSession openSession(Connection connection) {
    return originalFactory.openSession(connection);
  }

  @Override
  public SqlSession openSession(TransactionIsolationLevel level) {
    return originalFactory.openSession(level);
  }

  @Override
  public SqlSession openSession(ExecutorType execType) {
    return originalFactory.openSession(execType);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
    return originalFactory.openSession(execType, autoCommit);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
    return originalFactory.openSession(execType, level);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, Connection connection) {
    return originalFactory.openSession(execType, connection);
  }

  @Override
  public Configuration getConfiguration() {
    return originalFactory.getConfiguration();
  }


}
