package com.eomcs.spring.ioc.ex06.d;

import org.springframework.beans.factory.FactoryBean;
import com.eomcs.spring.ioc.ex06.Car;

// 스프링 IoC 컨테이너가 정한 규칙에 따라 공장 클래스를 만들면,
// 스프링 기법을 사용하여 보다 쉽게 객체 생성을 설정할 수 있다.
//
// 스프링에서 공장 클래스를 만들 때 제안한 규칙?
// => org.springframework.beans.factory.FactoryBean 인터페이스
//
public class CarFactory3 implements FactoryBean<Car>{
  String model;

  public CarFactory3() {
    System.out.println("CarFactory3() 생성자 호출됨.");
  }

  public void setModel(String model) {
    System.out.println("CarFactory3.setModel() 호출됨.");
    this.model = model;
  }

  // 이 메서드는 Spring IoC가 getBean(타입)으로 객체를 찾을때 사용한다.
  @Override
  public Class<?> getObjectType() {
    System.out.println("CarFactory3.getObjectType() 호출됨.");
    return Car.class;
//    System.out.println("String.class라고 타입 알려줌");
//    return String.class;
  }
  
  // 이 메서드는 Spring IoC가 getBean(인스턴스)으로 객체를 찾을때 사용한다.
  @Override
  public Car getObject() throws Exception {
    System.out.println("CarFactory3.getObject() 호출됨.");

    Car c = new Car();
    switch (model) {
      case "티코":
        c.setMaker("대우자동차");
        c.setModel("Tico");
        c.setCc(890);
        return c;
      case "소나타":
        c.setMaker("현대자동차");
        c.setModel("Sonata");
        c.setCc(1980);
        return c;
      case "SM5":
        c.setMaker("르노삼성자동차");
        c.setMaker("SM5");
        c.setCc(1990);
        return c;
      default:
        c.setMaker("비트자동차");
        c.setModel("자바휘웅");
        c.setCc(5000);
        return c;
    }
  }


}



