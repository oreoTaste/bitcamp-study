// 팩토리 메서드 호출 - FactoryBean 구현체
package com.eomcs.spring.ioc.ex06.d;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.ex06.Car;

public class Exam04 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex06/d/application-context-04.xml");

    //getObject로 체크
    System.out.println(iocContainer.getBean("c1"));

    //getObjectType로 체크
    System.out.println(iocContainer.getBean(Car.class));
  }

}





