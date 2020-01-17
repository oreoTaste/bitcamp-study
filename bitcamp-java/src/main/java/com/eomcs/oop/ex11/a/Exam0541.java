package com.eomcs.oop.ex11.a;
// anonymous class : 익명클래스가 놓이는 장소 - 파라미터 + 람다

public class Exam0541 {

  interface A {
    void print();
  }

  public static void main(final String[] args) {
    final Exam0541 e = new Exam0541();
    e.m1(() -> System.out.println("안녕"));
  }

  A obj = () -> System.out.println("Hello");

  void m1(final A obj) {
    obj.print();
  }
}
