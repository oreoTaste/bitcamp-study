// Mybatis - UPDATE SQL 실행하기
package com.eomcs.mybatis.ex02;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0250 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex02/mybatis-config08.xml");
    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // 벽여할 데이터를 객체에 담아서 넘긴다
    Board board = new Board();
    board.setNo(5);
    board.setTitle("5번(aaaa)");
    board.setContent("5번(bbbb)");

    int count = sqlSession.update("BoardMapper.updateBoard", board);
    System.out.println(count);

    sqlSession.commit();
    sqlSession.close();
  }

}











