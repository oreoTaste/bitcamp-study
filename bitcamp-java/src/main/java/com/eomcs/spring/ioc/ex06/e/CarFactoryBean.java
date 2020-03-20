package com.eomcs.spring.ioc.ex06.e;

import org.springframework.beans.factory.FactoryBean;
import com.eomcs.spring.ioc.ex06.Car;

// 보통 FactoryBean 구현체를 만들 때는 클래스 이름 뒤에 접미사로 FactoryBean을 붙여
// 다른 개발자가 쉽게 알아보도록 만든다.
//
public class CarFactoryBean implements FactoryBean<Car>{
  String model;

  public CarFactoryBean() {
    System.out.println("CarFactoryBean() 생성자 호출됨.");
  }

  public void setModel(String model) {
    System.out.println("CarFactoryBean.setModel() 호출됨.");
    this.model = model;
  }


  // 이 메서드는 Spring IoC가 getBean(타입)으로 객체를 찾을때 사용한다.
  @Override
  public Class<?> getObjectType() {
    System.out.println("CarFactoryBean.getObjectType() 호출됨.");
    return Car.class;
  }

  // 이 메서드는 Spring IoC가 getBean(인스턴스)으로 객체를 찾을때 사용한다.
  @Override
  public Car getObject() throws Exception {
    System.out.println("CarFactoryBean.getObject() 호출됨.");
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



