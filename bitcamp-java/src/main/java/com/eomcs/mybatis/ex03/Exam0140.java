// Dynamic sql 다루기 - WHERE 사용 후
package com.eomcs.mybatis.ex03;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0140 {

  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex03/mybatis-config.xml");
    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // dynamic sql :
    // 사용자로부터 검색키워드를 입력받아 조회한다.
    HashMap<String, Object> params = new HashMap<>();
    System.out.print("번호? ");
    String value = keyScan.nextLine();

    if(value.length() > 0) {
      params.put("no", value);
    }
    
    System.out.print("제목? ");
    value = keyScan.nextLine();
    if(value.length() > 0) {
      params.put("title", value);
    }

    System.out.print("내용? ");
    value = keyScan.nextLine();
    if(value.length() > 0) {
      params.put("content", value);
    }

    // SQL Mapper에 여러개의 파라미터를 넘기는법!
    // => 주로 Map을 사용한다.
    
    List<Board> list = null;
    
    try {
      // 게시글의 번호가 주어지면
      // 특정 게시글만 조회하는 select1 SQL문을 수행한다.
      list = sqlSession.selectList("BoardMapper.select5", params);
    } catch(Exception e) {
      // 게시글의 번호가 없으면,
      // 전체 게시글을 조회하는 select1 SQL문을 수행한다.
      e.printStackTrace();
      list = sqlSession.selectList("BoardMapper.select5");
    }

    for (Board board : list) {
      System.out.printf("%d, %s, %s, %s %d\n",
          board.getNo(),
          board.getTitle(),
          board.getContent(),
          board.getRegisteredDate(),
          board.getViewCount());
    }
    
    keyScan.close();
    sqlSession.close();

  }

}











