package com.eomcs.design_pattern.observer.after.h;

public interface CarObserver {
  // 자동차 시동을 켤때 호출될 메서드
  void carStarted();
  // default void carStarted() {} <- 추상메서드를 추가로 선언하지 않고 이렇게도 가능
  
  // 자동차 시동을 끌때 호출될 메서드
  void carStopped();
  // default void carStopped() {} <- 추상메서드를 추가로 선언하지 않고 이렇게도 가능
}
