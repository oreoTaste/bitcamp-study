// 객체 생성
package com.eomcs.spring.ioc.ex02.d;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam02 {

  // ClassPathXml, FilePathXml, AnnotationConfig

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex02/d/application-context-04.xml");


    // 첫번째 익명클래스만 별명을 갖게된다.
    SpringUtils.printBeanAliases(iocContainer, "com.eomcs.spring.ioc.ex02.Car");
    
    SpringUtils.printBeanAliases(iocContainer, "com.eomcs.spring.ioc.ex02.Car#1");

    
  }

}





