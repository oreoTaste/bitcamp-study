// non-static nested class
package com.eomcs.oop.ex11.a;

public class Exam0310 {
  void m1() {

    class A {
      void m1() {}
    }

    // 로컬클래스는 클래스가 정의되어있는 메서드 안에서만 사용가능.
    final A obj = new A();
    obj.m1();
  }

  void m2() {
    // 다른 메서드에 있는 로컬 클래스를 사용할 수 없다.
    // 이것은 다른 메서드에 있는 로컬 변수를 사용할 수 없는 것과 같다.

    // final A obj = new A();
  }
}
