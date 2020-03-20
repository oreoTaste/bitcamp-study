// 스프링 IoC 컨테이너 사용
package com.eomcs.spring.ioc.ex01.b;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Exam01 {
  public static void main(String[] args) {
    // ApplicationContext 구현체의 종류
    // (implements, 인터페이스를 구현한 클래스 또는 그 클래스의 인스턴스)
    // => XML 파일에서 설정 정보를 읽어들이는 IoC 컨테이너
    //    - ClassPathXmlApplicationContext : 설정 파일을 자바 CLASSPATH 경로에서 찾는다.
    //    - FileSystemXmlApplicationContext : 설정 파일을 OS 경로에서 찾는다.
    // => 자바 클래스 파일의 애노테이션에서 설정 정보를 읽어 들이는 IoC 컨테이너
    //    - AnnotationConfigApplicationContext : 설정 정보를 자바 클래스에서 찾는다.
    
//    ClassPathXml, FileSystemXml,
    
    // 2) 운영체제의 파일 시스템에서 설정 파일을 찾는 IoC 컨테이너
    // => 설정 파일 경로를 지정할 때 직접 파일 경로를 지정해야 한다.
    // => 주의!
    //    설정 파일 경로를 지정할 때 URL 형식으로 지정해야 한다.
    //    URL 형식에서는 파일 시스템을 가리킬 때 다음의 접두어를 붙인다.
    //    file://
    ApplicationContext iocContainer2 =
        new FileSystemXmlApplicationContext(
            "file:///Users/user/git/bitcamp-study/bitcamp-java/"
            + "src/main/java/com/eomcs/spring/ioc/ex01/b/application-context.xml");

    iocContainer2 =
        new FileSystemXmlApplicationContext(
            "file:///Users\\user\\git\\bitcamp-study\\bitcamp-java\\"
            + "src\\main\\java\\com\\eomcs\\spring\\ioc\\ex01\\b\\application-context.xml");

    
//    // 3) 자바 클래스 파일의 애노테이션으로부터 설정 정보를 추출한다. = java config 라 부른다.
//    // => 생성자에 설정 정보를 갖고 있는 클래스의 타입 정보를 넘긴다.
//    ApplicationContext iocContainer3 =
//        new AnnotationConfigApplicationContext(AppConfig.class);
    
    System.out.println("실행 완료!");
  }
}






