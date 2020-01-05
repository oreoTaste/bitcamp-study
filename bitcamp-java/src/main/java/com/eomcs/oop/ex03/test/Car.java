package com.eomcs.oop.ex03.test;
import com.eomcs.oop.ex03.test.sub.Engine;

public class Car {
  boolean on;
  int speed;
  String mode;
  String maker;
  Engine engine;
  int distance;

  Car() {
    this.engine = new Engine(4, 16, 1980);
  }
  
  void start() {
    this.on = true;
  }
  void stop() {
    this.on = false;
  }
  void check() {
    if(this.engine.oilState == 0) {
      this.engine.cleanOil();
      System.out.println("자동차의 엔진오일을 교체했습니다.");
    } System.out.println("자동차를 정비했습니다.");
  }
}
