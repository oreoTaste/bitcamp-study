// Mybatis - SQL에 파라미터 지정하기 : ${}
package com.eomcs.mybatis.ex02;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0220 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex02/mybatis-config05.xml");
    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // 파라미터로 컬럼이름을 넘겨주면, 해당 컬럼의 값을 오름차순으로 정렬한다.
    // 이때 #{}문법을 쓰면 안된다 <-- 값을 넣을때
    // ${}을 써야한다.            <-- 컬럼이름을 보낼때
    
    // mybatis에 SQL문을 만들어 전달할 수 있다.
    // => SQL 삽입 공격에 노출되기 때문에 이 방식을 사용하지 말라!
//    HashMap<String,Object> paramMap = new HashMap<>();
//    paramMap.put("limitSQL", "limit 9, 5");

    List<Board> list =
        sqlSession.selectList("BoardMapper.selectBoard1", "title");

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











