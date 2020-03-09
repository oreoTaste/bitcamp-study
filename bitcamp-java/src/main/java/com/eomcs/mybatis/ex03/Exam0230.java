// Dynamic sql 다루기 - <set> 사용후
package com.eomcs.mybatis.ex03;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0230 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex03/mybatis-config.xml");
    SqlSessionFactory factory =
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // dynamic sql :
    // 제목만 바꿀경우, 내용만 바꿀경우, 둘다 바꿀경우
    
    Scanner keyScan = new Scanner(System.in);
    String value = null;

    HashMap<String, Object> params = new HashMap<>();

    params.put("no", 1);
    
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
    

    int count = 0;
    count = sqlSession.update("BoardMapper.update4", params);
    System.out.println(count);
    
    sqlSession.commit();
    keyScan.close();
    sqlSession.close();

  }

}











