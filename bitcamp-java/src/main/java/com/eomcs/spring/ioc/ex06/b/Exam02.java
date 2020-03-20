// 팩토리 메서드 호출 - static 메서드 호출 응용
package com.eomcs.spring.ioc.ex06.b;

import java.sql.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam02 {

  public static void main(String[] args) {

    // ioc 컨테이너에서 스태틱메서드를 호출하여, 그 리턴값 보관하기.
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex06/b/application-context-02.xml");

    Date date = (Date) iocContainer.getBean("d1");
    System.out.println(date);
  }

}





