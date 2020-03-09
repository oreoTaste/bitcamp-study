// Dynamic sql 다루기 - <Foreach> 사용
package com.eomcs.mybatis.ex03;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0270 {

  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex03/mybatis-config.xml");
    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // dynamic sql :
    // 제목만 바꿀경우, 내용만 바꿀경우, 둘다 바꿀경우

    HashMap<String, Object> params = new HashMap<>();

    System.out.print("검색? ");
    String[] values = keyScan.nextLine().split(" ");
    
    List<Object> words = new ArrayList<>();
    for(String value : values) {
      words.add(value.trim());
      // no0 : 1
      // no1 : 6
      // no2 : 8 ..
    }
    
    params.put("words", words);

    List<Board> list = sqlSession.selectList("BoardMapper.select25", params);

    for (Board board : list) {
      System.out.printf("%d, %s, %s, %s %d\n",
          board.getNo(),
          board.getTitle(),
          board.getContent(),
          board.getRegisteredDate(),
          board.getViewCount());
    }
    
    sqlSession.commit();
    keyScan.close();
    sqlSession.close();

  }

}











