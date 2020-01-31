package com.eomcs.design_pattern.observer.after.h;

public class EngineOilCarObserver extends AbstractCarObserver {

  @Override
  public void carStarted() {
    System.out.println("엔진오일 유뮤 검사!");
  }

}
