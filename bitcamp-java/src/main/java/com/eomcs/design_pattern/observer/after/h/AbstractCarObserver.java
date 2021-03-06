package com.eomcs.design_pattern.observer.after.h;

public abstract class AbstractCarObserver implements CarObserver {

  @Override
  public void carStarted() {}
 
  @Override
  public void carStopped() {}

}
