package com.eomcs.oop.ex11.a;
// anonymous class : 익명클래스가 놓이는 장소 - instance field

public class Exam0520 {

  interface A {
    void print();
  }

  A obj = new A() {
    public void print() {
      System.out.println("Hello");
    } 
  };

}
