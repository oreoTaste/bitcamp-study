package com.eomcs.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DataSource {
  String jdbcUrl;
  String username;
  String password;

  // 스레드에 값을 보관하는 일을 할 도구 준비
  ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

  // 반납받은 커넥션을 보관할 저장소를 준비한다.
  ArrayList<Connection> conList = new ArrayList<>();

  public DataSource(String jdbcUrl, String username, String password) {
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
      // 2. 스레드에 보관된 커넥션 객체가 없다면
      // 2-1. 기존에 반납한 Connection 객체가 있는지 검사하기
      if(conList.size() > 0) {
        con = conList.remove(0);
        System.out.println("기존에 반납받은 객체 재사용");
      }

      // 2-2. 새로 Connection을 만들어 리턴하기
      con = new ConnectionProxy(
          DriverManager.getConnection(jdbcUrl, username, password));
      connectionLocal.set(con);
      System.out.println("신규 커넥션 Proxy 객체 리턴");
    }
    
    System.out.printf("현재보관중인 객체 : %d개\n", conList.size());

    return con;
  }

  public Connection removeConnection() throws Exception {
    // 스레드에 보관된 객체를 제거한다.
    Connection con = connectionLocal.get();
    if(con != null) {
      connectionLocal.remove();
      conList.add(con);
    }
    System.out.printf("현재보관중인 객체 : %d개\n", conList.size());
    return con;
  }
  
  
  public void clean() {
    // 보관소에 저장된 모든 커넥션을 닫는다.
    while(conList.size() > 0) {
      try {
        ((ConnectionProxy)conList.remove(0)).realClose();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  

}
