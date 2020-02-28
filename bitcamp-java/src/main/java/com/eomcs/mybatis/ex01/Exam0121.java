// Mybatis - 컬럼이름과 프로퍼티 이름 일치시키기
package com.eomcs.mybatis.ex01;

import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0121 {

  public static void main(String[] args) throws Exception {
    
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(
        Resources.getResourceAsStream("com/eomcs/mybatis/ex01/mybatis-config.xml"));
    
    SqlSession sqlSession = factory.openSession();

    List<Board> list =
        sqlSession.selectList("BoardMapper.selectBoard2");

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

/*
 * selectList 동작원리
 * 1. resultType에 지정한 클래스의 인스턴스를 생성한다.
 * 2. 컬럼이름과 일치하는 프로퍼티를 찾아 값을 입력한다.
 *    board_id => setBoard_id();
 *    title => setTitle();
 *    contents => setContents();
 *    created_date => setCreated_date();
 *    view_count => setView_count();
 */






