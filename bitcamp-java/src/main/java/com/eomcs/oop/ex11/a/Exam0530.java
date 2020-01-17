package com.eomcs.oop.ex11.a;
// anonymous class : 익명클래스가 놓이는 장소 - instance field

public class Exam0530 {

  interface A {
    void print();
  }

   void m1() {
    A obj = new A() {
      public void print() {
        System.out.println("Hello");
      } 
    };
    obj.print();
  }
  
  public static void main(String[] args) {
    Exam0530 e = new Exam0530();
    e.m1();
  }
}
