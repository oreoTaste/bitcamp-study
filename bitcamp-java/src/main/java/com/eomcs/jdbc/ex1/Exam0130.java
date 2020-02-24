// JDBC 드라이버 준비 - 드라이버 클래스 로딩과 등록
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;

public class Exam0130 {

  public static void main(String[] args) {

    // JDBC 드라이버 로딩 방법3: Driver 구현 클래스 로딩과 자동 등록
    // => java.sql.Driver 인터페이스를 구현한 클래스를 로딩하면
    // 해당 클래스에서 자신을 자동으로 DriverManager에게 등록할 것이다.
    // org.mariadb.jdbc.Driver 클래스의 소스를 확인해 보라!
    // static 블록에서 Driver 객체를 만들어 DriverManager에게 등록한다.
    //
    // => 이 방식의 장점은 소스 코드에 클래스를 지정하는 대신
    // 클래스 이름을 지정하기 때문에 실행할 때 다른 클래스로 쉽게 바꿀 수 있다.
    // => 따라서 특정한 Driver 구현체에 종속되지 않게 만들 수 있다.
    try {
      // 1) Class.forName("fully-qualified class name") : 패키지명을 포함한 클래스명
      // new org.mariadb.jdbc.Driver();
      Class.forName("org.mariadb.jdbc.Driver");

      // DriverManager에 자동 등록된 것을 확인해보자!
      java.sql.Driver driver = DriverManager.getDriver("jdbc:mariadb:");
      System.out.println("JDBC 드라이버 로딩 및 등록 완료!");

    } catch (Exception e) {
      System.out.println("MariaDB의 java.sql.Driver 구현체를 등록하는 중에 오류 발생!");
      e.printStackTrace();
    }
  }
}

