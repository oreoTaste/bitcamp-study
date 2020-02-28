// Mybatis - SQL에 파라미터 지정하기 : Map 넘기기
package com.eomcs.mybatis.ex02;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0212 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex02/mybatis-config04.xml");
    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // SQL을 실행할 때 파라미터 값을 전달하려면
    // 두 번째 파라미터로 전달해야 한다.
    // 여러 개의 값을 전달해야 한다면,
    // Map 객체에 담아 전달하라!
    HashMap<String,Object> params = new HashMap<>();
    params.put("startIndex", 0);
    params.put("size", 100);
 
    // 게시글 제목에 '제목'이 포함되어있는 게시글을 출력하라
    List<Board> list =
        sqlSession.selectList("BoardMapper.selectBoard3", params);
    // auto-boxing

    for (Board board : list) {
      System.out.printf("%d, %s, %s, %s, %d\n",
          board.getNo(),
          board.getTitle(),
          board.getContent(),
          board.getRegisteredDate(),
          board.getViewCount());
    }

    sqlSession.close();
  }

}











