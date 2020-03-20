// IoC 컨테이너에 보관된 객체를 확인해보자
package com.eomcs.spring.ioc.ex01.d;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {
  
  // ClassPathXml, FileSystemXml, AnnotationConfig
  
  public static void main(String[] args) {
    ApplicationContext iocContainer1 =
        new ClassPathXmlApplicationContext("com/eomcs/spring/ioc/ex01/d/application-context.xml");
    
    SpringUtils.printBeanList(iocContainer1);
    
    System.out.println("실행 완료!");
  }
}






