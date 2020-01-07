package com.eomcs.oop.ex08.test;

public class I extends Object{
  private int a;
  private int b;
  
  // getter, setter를 합쳐서 proterty라고 함.
  public int getA() {
    return a;
  }
  public void setA(int a) {
    this.a = a;
  }
  public int getB() {
    return b;
  }
  public void setB(int b) {
    this.b = b;
  }
}

class TestI {
  public static void main(String[] args) {
    I obj = new I();
    obj.setA(100);
    obj.setB(100);

    System.out.println(obj.getA());
    System.out.println(obj.getB());
  }
}
