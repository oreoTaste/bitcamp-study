package com.eomcs.design_pattern.observer.after.e;

public class LightOffCarObserver implements CarObserver {

  @Override
  public void carStarted() {
    
  }

  @Override
  public void carStopped() {
    System.out.println("전조증 소등!");
  }

}
