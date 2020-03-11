package com.eomcs.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
  String jdbcUrl;
  String username;
  String password;
  
  // 스레드에 값을 보관하는 일을 할 도구 준비
  ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

  public ConnectionFactory(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }
  
  public Connection getConnection() throws Exception {
    // 1. 스레드에 connection 객체가 있는지 확인
    Connection con = connectionLocal.get();
    
    if(con != null) {
      System.out.println("기존 커넥션 객체 리턴");
    } else {
    con = DriverManager.getConnection(jdbcUrl, username, password);
    connectionLocal.set(con);
    System.out.println("신규 커넥션 객체 리턴");
    }
    
    return con;
  }
  
  public void removeConnection() throws Exception {
    // 스레드에 보관된 객체를 제거한다.
    Connection con = connectionLocal.get();
    if(con != null) {
      connectionLocal.remove();
    }
  }
  
}
