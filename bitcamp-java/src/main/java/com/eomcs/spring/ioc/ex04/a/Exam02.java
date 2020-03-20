// 프로퍼티 호출
package com.eomcs.spring.ioc.ex04.a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam02 {

    public static void main(String[] args) {
        ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
                "com/eomcs/spring/ioc/ex04/a/application-context-01.xml");
        
        System.out.println("---------------------------------------");
        System.out.println(iocContainer.getBean("c1"));
        System.out.println("---------------------------------------");
        System.out.println(iocContainer.getBean("c2"));
        System.out.println("---------------------------------------");
        System.out.println(iocContainer.getBean("c3"));
        System.out.println("---------------------------------------");
        System.out.println(iocContainer.getBean("c4"));
    }

}





