// 프로퍼티 에디터 - String ==> java.sql.Date ; 변환기 사용하여 처리하기
package com.eomcs.spring.ioc.ex07.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam03 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex07/c/application-context-03.xml");

    System.out.println(iocContainer.getBean("c1"));
    
    
  }

}





