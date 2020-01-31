package com.eomcs.design_pattern.observer.after.h;

public class SunRoofCloseCarObserver extends AbstractCarObserver {

  @Override
  public void carStopped() {
    System.out.println("썬루프 닫기!");
  }

}
