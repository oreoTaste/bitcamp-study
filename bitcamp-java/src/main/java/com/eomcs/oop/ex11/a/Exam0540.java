package com.eomcs.oop.ex11.a;
// anonymous class : 익명클래스가 놓이는 장소 - 파라미터

public class Exam0540 {

  interface A {
    void print();
  }
  
  A obj = new A() {
    public void print() {
      System.out.println("Hello");
    } 
  };

   void m1(A obj) {
     obj.print();
  }
  
  public static void main(String[] args) {
    Exam0540 e = new Exam0540();
    e.m1(new A() {
      public void print() {
        System.out.println("안녕");
      }
    });
  }
}
