// IoC 컨테이너에 보관하기
package com.eomcs.spring.ioc.ex01.e_classPath;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {
  
  // ClassPathXml, FileSystemXml, AnnotationConfig

  public static void main(String[] args) {
    ApplicationContext iocContainer1 =
        new ClassPathXmlApplicationContext("com/eomcs/spring/ioc/ex01/e_classPath/application-context.xml");
    
    SpringUtils.printBeanList(iocContainer1);
    System.out.println("실행 완료!");
  }
}






