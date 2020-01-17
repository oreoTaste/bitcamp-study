package com.eomcs.oop.ex11.a;

public class Exam0410 {

  interface A {
    void print();
  }

  public static void main(final String[] args) {

    // 익명클래스
    final A obj = new A() {
      @Override
      public void print() {
        System.out.println("Hello");
      }
    };

    obj.print();

  }
}
