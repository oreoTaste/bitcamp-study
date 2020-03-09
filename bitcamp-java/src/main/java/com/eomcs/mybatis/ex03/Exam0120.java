// Dynamic sql 다루기 - 조건문 사용 후
package com.eomcs.mybatis.ex03;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0120 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex03/mybatis-config.xml");
    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // dynamic sql :
    // 조건에 따라 sql을 달리 생성하는것

    Scanner keyScan = new Scanner(System.in);
    System.out.print("게시글 번호? ");
    String no = keyScan.nextLine();
    keyScan.close();
    List<Board> list = null;
    
    try {
      // 게시글의 번호가 주어지면
      // 특정 게시글만 조회하는 select1 SQL문을 수행한다.
      list = sqlSession.selectList("BoardMapper.select3", Integer.parseInt(no));
    } catch(Exception e) {
      // 게시글의 번호가 없으면,
      // 전체 게시글을 조회하는 select1 SQL문을 수행한다.
      e.printStackTrace();
      list = sqlSession.selectList("BoardMapper.select3");
    }

    for (Board board : list) {
      System.out.printf("%d, %s, %s, %s\n",
          board.getNo(),
          board.getTitle(),
          board.getContent(),
          board.getRegisteredDate());
    }

    sqlSession.close();
  }

}











