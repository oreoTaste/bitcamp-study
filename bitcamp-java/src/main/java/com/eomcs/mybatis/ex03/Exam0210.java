// Dynamic sql 다루기 - choose when
package com.eomcs.mybatis.ex03;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0210 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex03/mybatis-config.xml");
    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // dynamic sql :
    // 사용자로부터 검색키워드를 입력받아 조회한다.

    
    Scanner keyScan = new Scanner(System.in);
    String item = null, keyword = null;
    System.out.print("항목? (1.번호, 2.제목, 그외:내용) ");
    item = keyScan.nextLine();
    
    System.out.print("검색어? ");
    keyword = keyScan.nextLine();
    
    HashMap<String, Object> params = new HashMap<>();
    if(item.equals("1")) {
      params.put("item", "no");
    } else if(item.equals("2")) {
      params.put("item", "title");
    } else {
      params.put("item", "content");
    }
    params.put("keyword", keyword);
    
    List<Board> list = null;
    
    try {
      // 게시글의 번호가 주어지면
      // 특정 게시글만 조회하는 select1 SQL문을 수행한다.
      list = sqlSession.selectList("BoardMapper.select21", params);
    } catch(Exception e) {
      // 게시글의 번호가 없으면,
      // 전체 게시글을 조회하는 select1 SQL문을 수행한다.
      e.printStackTrace();
      list = sqlSession.selectList("BoardMapper.select21");
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











