// Dynamic sql 다루기 - <bind> 사용
package com.eomcs.mybatis.ex03;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0280 {

  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex03/mybatis-config.xml");
    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // bind문을 사용하면
    // like문의 문자열 패턴을 만들때 유용하다.

    HashMap<String, Object> params = new HashMap<>();

    System.out.print("제목? ");
    String value = keyScan.nextLine();
    params.put("title", value);
    
    List<Board> list = sqlSession.selectList("BoardMapper2.select26", params);

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











