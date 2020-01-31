package com.eomcs.design_pattern.observer.after.d;

public class EngineOilCarObserver implements CarObserver {

  @Override
  public void carStarted() {
    System.out.println("엔진오일 유뮤 검사!");
  }

  @Override
  public void carStopped() {
  }

}
