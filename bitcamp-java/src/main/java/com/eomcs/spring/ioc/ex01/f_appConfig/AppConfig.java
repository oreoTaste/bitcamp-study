package com.eomcs.spring.ioc.ex01.f_appConfig;

import org.springframework.context.annotation.Bean;
import com.eomcs.spring.ioc.ex01.Car;

// 클래스 선언부에 애노테이션으로
// Spring 설정에 관한 정보를 지정할 수 있다.
public class AppConfig {
  
  @Bean
  // 필드나 메서드로 Spring 관련 설정을 수행할 수 있다.
  public Car c1() {
    return new Car();
  }
  
}
