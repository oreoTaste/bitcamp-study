// 의존 객체 주입 자동화하기 - BeanPostProcessor 동작 원리
package com.eomcs.spring.ioc.ex08.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam03 {

  public static void main(String[] args) {
    //
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex08/c/application-context-03.xml");

    SpringUtils.printBeanList(iocContainer);
    
    // Car 객체를 꺼내 Engine 객체가 주입되었는지 확인해보자!
    System.out.println(iocContainer.getBean("c1"));
  }

}





