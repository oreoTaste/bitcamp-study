// IoC 컨테이너에서 꺼내기
package com.eomcs.spring.ioc.ex01.g_classPath;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam02 {
  
  // ClassPathXml, FileSystemXml, AnnotationConfig

  public static void main(String[] args) {
    ApplicationContext iocContainer1 =
        new ClassPathXmlApplicationContext("com/eomcs/spring/ioc/ex01/g_classPath/application-context.xml");
    
    SpringUtils.printBeanList(iocContainer1);
    System.out.println(iocContainer1.getBean("c2")); // 존재하지 않는 객체 꺼내기 -> null이 아니라 error 발생
    System.out.println("실행 완료!");
  }
}






