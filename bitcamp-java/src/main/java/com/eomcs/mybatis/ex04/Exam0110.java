// 조인 데이터 가져오기 : 조인 사용하기 전
package com.eomcs.mybatis.ex04;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0110 {
  static Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex04/mybatis-config.xml");
    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    System.out.print("게시글번호? ");
    int no = Integer.parseInt(keyScan.nextLine());
    
    Board board = sqlSession.selectOne("BoardMapper.selectBoard", no);

    System.out.println("[게시글조회]");
    System.out.printf("번호 : %d\n", board.getNo());
    System.out.printf("제목 : %s\n", board.getTitle());
    System.out.printf("내용 : %s\n", board.getContent());
    System.out.printf("등록일 : %s\n", board.getRegisteredDate());
    System.out.printf("조회수 : %d\n", board.getViewCount());

    System.out.println();
     
    List<AttachFile> list = sqlSession.selectList("BoardMapper.selectFile", no);
    System.out.println("[첨부파일]");
    for(AttachFile file : list) {
      System.out.printf("%d. %s\n", file.getNo(), file.getFilePath());
    }
    
    keyScan.close();
    sqlSession.close();

  }

}











