package com.eomcs.oop.ex11.a;

public class Exam0421 {

  interface A {
    void print();
  }

  public static void main(final String[] args) {

    // 익명클래스
    final A obj = () -> System.out.println("Hello");

    obj.print();
  }
}
