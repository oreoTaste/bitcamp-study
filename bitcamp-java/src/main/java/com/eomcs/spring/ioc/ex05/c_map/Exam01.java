// 컬렉션 프로퍼티의 값 설정
package com.eomcs.spring.ioc.ex05.c_map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex05/c/application-context-03.xml");

    System.out.println(iocContainer.getBean("c1"));
    System.out.println(iocContainer.getBean("e1"));
  }

}





