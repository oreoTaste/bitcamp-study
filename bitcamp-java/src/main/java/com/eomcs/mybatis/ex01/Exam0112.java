// Data Persistence Framework 도입 - Mybatis 설정파일의 InputStream얻는방법
package com.eomcs.mybatis.ex01;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0112 {

  public static void main(String[] args) throws Exception {

    //    InputStream mybatisConfigInputStream = new FileInputStream(
    //        "./bin/main/com/eomcs/mybatis/ex01/mybatis-config.xml");
    // 간편히 하기 위해..도우미 객체 도입!
    // Resources클래스의 메서드 활용

    // => Resources의 getResourceAsStream() 메서드는
    //    파라미터에 지정한 파일 경로를 CLASSPATH에서 찾는다.
    //    그리고 그 파일을 읽을 수 있는 InputStream 도구를 리턴한다.
    // => 개발자가 mybatis-config.xml 파일의 전체 경로를 지정하지 않아도 되기 때문에
    //    매우 편리하다.
    // => 단 해당 파일은 CLASSPATH에 있어야 한다.

    // 2. sqlSessionFactory를 만들어줄 builder객체준비
    // 3. sqlSession객체를 만들어줄 팩터리 객체 준비.
    // mybatis 설정 파일을 가지고 Builder를 이용하여
    // SqlSession 공장 객체를 생성한다.
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(
            Resources.getResourceAsStream("com/eomcs/mybatis/ex01/mybatis-config.xml"));

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







