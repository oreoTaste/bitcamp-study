// static nested class
package com.eomcs.oop.ex11.a;

public class Exam0230 {
  public static void main(final String[] args) {
    final Exam0230_X exam0230_x = new Exam0230_X();
    exam0230_x.m1();

    Exam0230_X.A a;
    // a = new Exam0230_X.A();
    // 바깥 클래스명(Exam0230_X)이 아니라 "인스턴스"(exam0230_x)를 지정해야한다.
    a = exam0230_x.new A();
    // 바깥 클래스명(Exam0230_X)이 아니라 "인스턴스"(exam0230_x)를 지정해야한다.
  }
}


class Exam0230_X {
  class A {
    /*
     * 컴파일러가 추가하는 필드 및 생성자 Exam0230_X outer; public A (Exam0230_X obj) { outer = obj; }
     */
    void m1() {
      System.out.println("A의 m1");
    }
  }

  int sValue;

  void m1() {
    System.out.println("x의 m1");
  }
}
