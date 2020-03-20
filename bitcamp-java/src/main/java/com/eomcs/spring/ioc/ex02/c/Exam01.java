// 객체 생성
package com.eomcs.spring.ioc.ex02.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.ex02.Car;

public class Exam01 {
  
// ClassPathXml, FilePathXml, AnnotationConfig
  
  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex02/c/application-context-03.xml");

    // 정의된 Bean Name 출력 (getBeanDefinitionName)
    String[] names = iocContainer.getBeanDefinitionNames();
    for(String name : names) {
      System.out.println(name);
    }
    
    System.out.println("===========================================");
    
    // single tone으로 생성된 객체는
    // getBean을 여러번 생성하더라도 오직 1개만 생성된다.
    Car obj1 = (Car) iocContainer.getBean("c1");
    Car obj2 = (Car) iocContainer.getBean("c1");
    Car obj3 = (Car) iocContainer.getBean("c1");
    System.out.println(obj1 == obj2);
    System.out.println(obj1 == obj3);


    System.out.println("===========================================");
    // prototype 객체는
    // getBean을 여러번 생성하면 다른 객체로 매번 생성된다.(잘안쓴다)
    Car obj10 = (Car) iocContainer.getBean("c3");
    System.out.println("-------------------------------------------");
    Car obj20 = (Car) iocContainer.getBean("c3");
    System.out.println("-------------------------------------------");
    Car obj30 = (Car) iocContainer.getBean("c3");
    System.out.println("-------------------------------------------");
    System.out.println(obj10 == obj20);
    System.out.println(obj10 == obj30);

    
  }

}





