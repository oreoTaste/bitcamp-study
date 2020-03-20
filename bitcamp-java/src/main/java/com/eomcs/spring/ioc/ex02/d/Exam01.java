// 객체 생성
package com.eomcs.spring.ioc.ex02.d;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {

  // ClassPathXml, FilePathXml, AnnotationConfig

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex02/d/application-context-04.xml");


    // 익명클래스는 0부터 시작한다.
    String[] names = iocContainer.getBeanDefinitionNames();
    for(String name : names) {
      System.out.println(name);
    }

    
  }

}





