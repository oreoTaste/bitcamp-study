// Mybatis - DELETE SQL 실행하기
package com.eomcs.mybatis.ex02;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0260 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex02/mybatis-config09.xml");
    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // 자식테이블 데이터 지우기
    int count = sqlSession.delete("BoardMapper.deleteBoardFile", 3);
    System.out.println(count);
    
    // 부모테이블 데이터 지우기
    count = sqlSession.delete("BoardMapper.deleteBoard", 3);
    System.out.println(count);

    sqlSession.commit();
    sqlSession.close();
  }

}











