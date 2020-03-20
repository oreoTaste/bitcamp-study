// 스프링 IoC 컨테이너 사용
package com.eomcs.spring.ioc.ex01.f_appConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {
  
  // ClassPathXml, FileSystemXml, AnnotationConfig

  
  public static void main(String[] args) {
    // ApplicationContext 구현체(implements, 인터페이스를 구현한 클래스 또는 그 클래스의 인스턴스)의 종류
    // => XML 파일에서 설정 정보를 읽어들이는 IoC 컨테이너
    //    - ClassPathXmlApplicationContext : 설정 파일을 자바 CLASSPATH 경로에서 찾는다.
    //    - FileSystemXmlApplicationContext : 설정 파일을 OS 경로에서 찾는다.
    // => 자바 클래스 파일의 애노테이션에서 설정 정보를 읽어 들이는 IoC 컨테이너
    //    - AnnotationConfigApplicationContext : 설정 정보를 자바 클래스에서 찾는다.

//  ClassPathXml, FileSystemXml, AnnotationConfig
    
    // 3) 자바 클래스 파일의 애노테이션으로부터 설정 정보를 추출한다. = java config 라 부른다.
    // => 생성자에 설정 정보를 갖고 있는 클래스의 타입 정보를 넘긴다.
    ApplicationContext iocContainer3 =
        new AnnotationConfigApplicationContext(AppConfig.class);
    
    SpringUtils.printBeanList(iocContainer3);
    // AnnotationConfig에서 IoC 컨테이너를 만들었다면,
    // 기본적으로 Annotation 처리할때 사용할 기본 도구가 5개 생성된다.
    // 그외에도 AppConfig에서 생성한 객체도 생성이 된다.(Car객체)
    System.out.println("실행 완료!");
  }
}






