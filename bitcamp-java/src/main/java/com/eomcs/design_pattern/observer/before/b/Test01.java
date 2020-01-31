package com.eomcs.design_pattern.observer.before.b;

public class Test01 {

  public static void main(String[] args) {
    Car car = new Car();
    
    car.start();
    
    car.run();
    
    car.stop();
    
    // 프로젝트 완료한 다음 시간이 지난 후
    // 1) 자동차의 시동을 걸 때 안전벨트 착용 여부를 검사하는 기능을 추가한다.
    // => 기존 car 클래스의 start()메소드에 코드를 추가한다.
  }

}








