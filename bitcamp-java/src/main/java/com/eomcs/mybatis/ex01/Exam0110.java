// Data Persistence Framework 도입 - Mybatis
package com.eomcs.mybatis.ex01;

import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// Data Persistence Framework
// => 데이터의 영속성(지속성; 등록,조회,변경,삭제)을 대신 처리해주는 프레임워크.
// 1) SQL Mapper
//    - 직접 SQL 문을 작성
//    - 각각의 DBMS에 최적화된 SQL을 작성할 수 있다.
//    - DBMS마다 미미하게 다른 SQL을 작성해야 하는 번거로움이 있다.
//    - 예) Mybatis 등
// 2) OR Mapper
//    - 전용언어 및 문법(Domain-Specific Language;DSL)을 사용하여 작성
//      실행할 때 DBMS에 맞춰서 SQL을 생성하여 실행한다.
//    - DBMS에 마다 SQL문을 작성할 필요가 없어 편리하다.
//    - DBMS에 최적화된 SQL을 실행할 수 없다.
//      즉 DBMS의 특징을 최대로 활용할 수 없다.
//    - 예) Hibernate, TopLink 등
//
// Mybatis 도입
// 1) 의존 라이브러리 추가
//    - mvnrepository.com 검색하여 build.gradle 파일에 추가한다.
//    - "gradle eclipse" 실행한 후 이클립스 에디터에서 프로젝트를 "refresh" 한다.
// 2) mybatis 설정 파일 준비
//    - mybatis-config.xml 생성 및 편집
// 3) DB 연결 정보를 담은 프로퍼티 파일 준비
//    - jdbc.properties 생성 및 편집
// 4) SQL 문장을 작성할 파일 준비
//    - BoardMapper.xml 생성 및 편집
// 5) Mybatis 객체 준비
//
public class Exam0110 {

  public static void main(String[] args) throws Exception {
    // 1. mybatis 설정 파일을 읽을 도구(InputStream)를 준비한다.
    // 일반 파일은 그대로 빌드 디렉토리에 복사된다.
    // /bin/main/com/eomcs/mybatis/ex01/mybatis-config.xml
    InputStream mybatisConfigInputStream = new FileInputStream(
        "./bin/main/com/eomcs/mybatis/ex01/mybatis-config.xml");
    

    // 2. sqlSessionFactory를 만들어줄 builder객체준비
    SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
    // 3. sqlSession객체를 만들어줄 팩터리 객체 준비.
    // mybatis 설정 파일을 가지고 Builder를 이용하여
    // SqlSession 공장 객체를 생성한다.
    SqlSessionFactory factory =
        factoryBuilder.build(mybatisConfigInputStream);

    // 4. SQL을 실행시키는 객체준비
    // SqlSessionFactory 객체로부터 SqlSession 객체를 얻는다.
    // openSession()은 수동커밋으로 SQL을 다룬다.
    SqlSession sqlSession = factory.openSession();
    // autoCommit()을 true로 하고싶다면,
    // openSession(boolean autoCommit)메서드

    System.out.println("mybatis 준비 완료!");

    sqlSession.close();
  }

}







