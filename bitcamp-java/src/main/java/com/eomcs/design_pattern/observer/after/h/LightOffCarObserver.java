package com.eomcs.design_pattern.observer.after.h;

public class LightOffCarObserver extends AbstractCarObserver {

  @Override
  public void carStopped() {
    System.out.println("전조증 소등!");
  }

}
