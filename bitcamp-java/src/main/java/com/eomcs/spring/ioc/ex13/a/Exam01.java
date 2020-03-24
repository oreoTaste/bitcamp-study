// AOP(Aspect-Oriented Programming) - AOP 필터 적용 전
package com.eomcs.spring.ioc.ex13.a;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext iocContainer =
        new ClassPathXmlApplicationContext(
            "com/eomcs/spring/ioc/ex13/a/application-context-01.xml");

    SpringUtils.printBeanList(iocContainer);

    Caller caller = iocContainer.getBean(Caller.class);
    caller.test();
  }

}



