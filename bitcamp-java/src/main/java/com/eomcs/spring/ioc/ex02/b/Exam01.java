// 객체 생성
package com.eomcs.spring.ioc.ex02.b;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {
  
  // ClassPathXml, FileSystemXml, AnnotationConfig
  
  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex02/b/application-context-02.xml");

    // bean의 id와 class명 출력하기
    SpringUtils.printBeanList(iocContainer);
    
    // bean의 별명 알아내기
    // id, name 둘다 있는 경우
    String[] aliases3 = iocContainer.getAliases("c2");
    System.out.print("[별명] ");
    for(String aliase : aliases3) {
      System.out.print(aliase + " ");
    }
    
    // id만 지정한 경우
    String[] aliases1 = iocContainer.getAliases("c1");
    System.out.print("[별명] ");
    for(String aliase : aliases1) {
      System.out.print(aliase + " ");
    }
    
    // name만 지정한 경우
    String[] aliases2 = iocContainer.getAliases("c4");
    System.out.print("[별명] ");
    for(String aliase : aliases2) {
      System.out.print(aliase + " ");
    }
    
    
  }

}





