// Mybatis - SQL 문 실행
package com.eomcs.mybatis.ex01;

import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0120 {

  public static void main(String[] args) throws Exception {
    
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(
        Resources.getResourceAsStream("com/eomcs/mybatis/ex01/mybatis-config.xml"));
    
    SqlSession sqlSession = factory.openSession();

    // SqlSession 객체를 이용하여 SQL 맵퍼 파일에 작성한 SQL 문을 실행한다.
    // => select 문장
    //    - sqlSession.selectList() : 목록 리턴
    //    - sqlSession.selectOne() : 한 개의 결과 리턴
    // => insert 문장
    //    - sqlSession.insert()
    // => update 문장
    //    - sqlSession.update()
    // => delete 문장
    //    - sqlSession.delete()
    // => insert/update/delete인 경우 insert()/update()/delete() 메서드 중
    //    아무거나 호출해도 된다.
    //    하지만 일관된 유지보수를 위해 메서드를 구분하여 사용하라!
    //
    // 메서드 사용법)
    // => 예) selectList(SQL문이름, 파라미터값)
    //    - SQL문 이름 = 그룹명 + "." + SQL문장 아이디
    //    - 파라미터 값 = primitive type 및 모든 자바 객체가 가능하다.
    //                 여러 개의 값을 전달할 때는 Map에 담아 넘겨라!
    List<Board> list =
        // sqlSession.selectList("SQL 식별자");
        // SQL 식별자? 그룹명 + . + SQL문장 아이디
        // mapper namesapce = 그룹명
        // SQL문장 아이디? select id = SQL 문장 아이디
        sqlSession.selectList("BoardMapper.selectBoard");

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






