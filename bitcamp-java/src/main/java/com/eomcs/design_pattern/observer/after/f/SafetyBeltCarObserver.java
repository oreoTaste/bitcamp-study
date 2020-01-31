package com.eomcs.design_pattern.observer.after.f;

public class SafetyBeltCarObserver implements CarObserver {

  @Override
  public void carStarted() {
    System.out.println("안전벨트 착용여부 검사!");
  }

  @Override
  public void carStopped() {
  }

}
