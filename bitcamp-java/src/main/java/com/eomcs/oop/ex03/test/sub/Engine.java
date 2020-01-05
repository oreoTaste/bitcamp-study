package com.eomcs.oop.ex03.test.sub;

public class Engine {
  public int valve;
  public int sylinder;
  public int cc;
  public int oilState;
  
  public Engine(int sylinder, int valve, int cc) {
    this.sylinder = sylinder;
    this.valve = valve;
    this.cc = cc;
    this.oilState = 0;
  }
  
  public void cleanOil() {
    this.oilState = 10;
  }
}
