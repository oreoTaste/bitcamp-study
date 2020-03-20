// 객체 생성
package com.eomcs.spring.ioc.ex02.b;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam02 {

  // ClassPathXml, FileSystemXml, AnnotationConfig

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex02/b/application-context-02.xml");

    String[] names = iocContainer.getBeanDefinitionNames();
    for(String name : names) {
      System.out.print(name + " ");
      String[] aliases3 = iocContainer.getAliases(name);
      System.out.print("[별명] ");
      for(String aliase : aliases3) {
        System.out.print(aliase + " ");
      }
      System.out.println();
    }


  }

}





