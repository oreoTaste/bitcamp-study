// 프로퍼티 호출 - 객체 주입 시 객체 생성 순서
package com.eomcs.spring.ioc.ex04.d;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam03 {

    public static void main(String[] args) {
        ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
                "com/eomcs/spring/ioc/ex04/d/application-context-03.xml");
        
        System.out.println(iocContainer.getBean("c1"));
        System.out.println(iocContainer.getBean("e1"));
    }

}





