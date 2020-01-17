package com.eomcs.oop.ex11.a;
// anonymous class : 익명클래스가 놓이는 장소 - static field

public class Exam0510 {

  interface A {
    void print();
  }

  static A obj = new A() {
    public void print() {
      System.out.println("Hello");
    }
  };

}
