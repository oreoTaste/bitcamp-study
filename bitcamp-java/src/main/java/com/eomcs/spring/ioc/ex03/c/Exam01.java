// 생성자 호출
package com.eomcs.spring.ioc.ex03.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex03/c/application-context-02.xml");

//    SpringUtils.printBeanNames(iocContainer);
  }

}





