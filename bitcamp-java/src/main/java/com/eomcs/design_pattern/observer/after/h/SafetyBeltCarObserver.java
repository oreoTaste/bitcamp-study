package com.eomcs.design_pattern.observer.after.h;

public class SafetyBeltCarObserver extends AbstractCarObserver {

  @Override
  public void carStarted() {
    System.out.println("안전벨트 착용여부 검사!");
  }

}
