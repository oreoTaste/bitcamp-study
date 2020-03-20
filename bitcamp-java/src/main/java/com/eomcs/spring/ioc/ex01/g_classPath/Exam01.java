// IoC 컨테이너에서 꺼내기
package com.eomcs.spring.ioc.ex01.g_classPath;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;
import com.eomcs.spring.ioc.ex01.Car;

public class Exam01 {
  
  // ClassPathXml, FileSystemXml, AnnotationConfig

  public static void main(String[] args) {
    ApplicationContext iocContainer1 =
        new ClassPathXmlApplicationContext("com/eomcs/spring/ioc/ex01/g_classPath/application-context.xml");
    
    SpringUtils.printBeanList(iocContainer1);
    System.out.println(iocContainer1.getBean("c1"));
    System.out.println(iocContainer1.getBean(Car.class));
    System.out.println("실행 완료!");
  }
}






