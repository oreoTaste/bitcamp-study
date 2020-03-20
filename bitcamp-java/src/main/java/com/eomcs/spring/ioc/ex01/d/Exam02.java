// IoC 컨테이너에 보관된 객체를 확인해보자
package com.eomcs.spring.ioc.ex01.d;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam02 {
  
  // ClassPathXml, FileSystemXml, AnnotationConfig
  
  public static void main(String[] args) {
    ApplicationContext iocContainer2 = new FileSystemXmlApplicationContext(
        "file:///Users/user/git/bitcamp-study/bitcamp-java/src/main/java/"
        + "com/eomcs/spring/ioc/ex01/d/application-context.xml");
    
    SpringUtils.printBeanList(iocContainer2);
    
    System.out.println("실행 완료!");
  }
}






