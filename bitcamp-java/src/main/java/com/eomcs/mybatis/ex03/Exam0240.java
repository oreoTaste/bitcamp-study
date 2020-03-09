// Dynamic sql 다루기 - <Foreach> 사용전
package com.eomcs.mybatis.ex03;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0240 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex03/mybatis-config.xml");
    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // dynamic sql :
    // 제목만 바꿀경우, 내용만 바꿀경우, 둘다 바꿀경우
    
    Scanner keyScan = new Scanner(System.in);

    HashMap<String, Object> params = new HashMap<>();

    System.out.print("조회할 게시물 번호들(예: 1 6 8 10 // 최대 5개)? ");
    String[] values = keyScan.nextLine().split(" ");
    int index = 0;
    for(String value : values) {
      params.put("no" + (index++), value);
      // no0 : 1
      // no1 : 6
      // no2 : 8 ..
    }

    List<Board> list = sqlSession.selectList("BoardMapper.select22", params);

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











