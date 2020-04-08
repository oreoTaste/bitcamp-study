package com.eomcs.sql;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

public class SqlSessionProxy implements SqlSession {
  
  SqlSession originalSqlSession;
  
  public SqlSessionProxy(SqlSession originalSqlSession) {
    this.originalSqlSession = originalSqlSession;
  }
  
  @Override
  public void close() {
//    닫지 않는다.
//    originalSqlSession.close();
    System.out.println("SqlSession 안닫음");
  }
  
  public void realClose() {
    System.out.println("SqlSession 닫음");
    originalSqlSession.close();
  }
  
  
  @Override
  public <T> T selectOne(String statement) {
    return originalSqlSession.selectOne(statement);
  }

  @Override
  public <T> T selectOne(String statement, Object parameter) {
    return originalSqlSession.selectOne(statement, parameter);
  }

  @Override
  public <E> List<E> selectList(String statement) {
    return originalSqlSession.selectList(statement);
  }

  @Override
  public <E> List<E> selectList(String statement, Object parameter) {
    return originalSqlSession.selectList(statement, parameter);
  }

  @Override
  public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
    return originalSqlSession.selectList(statement, parameter, rowBounds);
  }

  @Override
  public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
    return originalSqlSession.selectMap(statement, mapKey);
  }

  @Override
  public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
    return originalSqlSession.selectMap(statement, parameter, mapKey);
  }

  @Override
  public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey,
      RowBounds rowBounds) {
    return originalSqlSession.selectMap(statement, parameter, mapKey, rowBounds);
  }

  @Override
  public <T> Cursor<T> selectCursor(String statement) {
    return originalSqlSession.selectCursor(statement);
  }

  @Override
  public <T> Cursor<T> selectCursor(String statement, Object parameter) {
    return originalSqlSession.selectCursor(statement, parameter);
  }

  @Override
  public <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds) {
    return originalSqlSession.selectCursor(statement, parameter, rowBounds);
  }

  @SuppressWarnings("rawtypes")
  @Override
  public void select(String statement, Object parameter, ResultHandler handler) {
    originalSqlSession.select(statement, parameter, handler);
  }

  @SuppressWarnings("rawtypes")
  @Override
  public void select(String statement, ResultHandler handler) {
    originalSqlSession.select(statement, handler);
  }

  @SuppressWarnings("rawtypes")
  @Override
  public void select(String statement, Object parameter, RowBounds rowBounds,
      ResultHandler handler) {
    originalSqlSession.select(statement, parameter, rowBounds, handler);
  }

  @Override
  public int insert(String statement) {
    return originalSqlSession.insert(statement);
  }

  @Override
  public int insert(String statement, Object parameter) {
    return originalSqlSession.insert(statement, parameter);
  }

  @Override
  public int update(String statement) {
    return originalSqlSession.update(statement);
  }

  @Override
  public int update(String statement, Object parameter) {
    return originalSqlSession.update(statement, parameter);
  }

  @Override
  public int delete(String statement) {
    return originalSqlSession.delete(statement);
  }

  @Override
  public int delete(String statement, Object parameter) {
    return originalSqlSession.delete(statement, parameter);
  }

  @Override
  public void commit() {
    originalSqlSession.commit();
  }

  @Override
  public void commit(boolean force) {
    originalSqlSession.commit(force);
  }

  @Override
  public void rollback() {
    originalSqlSession.rollback();
  }

  @Override
  public void rollback(boolean force) {
    originalSqlSession.rollback(force);
  }

  @Override
  public List<BatchResult> flushStatements() {
    return originalSqlSession.flushStatements();
  }

  @Override
  public void clearCache() {
    originalSqlSession.clearCache();
  }

  @Override
  public Configuration getConfiguration() {
    return originalSqlSession.getConfiguration();
  }

  @Override
  public <T> T getMapper(Class<T> type) {
    return originalSqlSession.getMapper(type);
  }

  @Override
  public Connection getConnection() {
    return originalSqlSession.getConnection();
  }

  
  
  
}
