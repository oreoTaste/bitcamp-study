package com.eomcs.design_pattern.observer.after.b;

public class Test01 {

  public static void main(String[] args) {
    Car car = new Car();
    car.addCarObserver(new SafetyBeltCarObserver());
    
    car.start();
    
    car.run();
    
    car.stop();
    
    // 프로젝트 완료한 다음 시간이 지난 후
    // 1) 자동차의 시동을 걸 때 안전벨트 착용 여부를 검사하는 기능을 추가한다.
    // => 자동차의 시동을 걸때 보고받을 객체를 준비하고
    // => 거기서 안전벨트 착용여부를 검사하는 메서드 생성
    // => SafeBeltCarObserver
  }

}








