// local class - 인스턴스 멤버에 선언된 로컬클래스
package com.eomcs.oop.ex11.a;

public class Exam0350 {

  public static void main(final String[] args) {
    final Exam0350 obj = new Exam0350();
    obj.m1();
  }

  void m1() {
    final int a = 200;

    class A {
      void m1() {
        // 로컬 클래스는 자신이 선언된 그 메서드의 로컬변수를 사용할 수 있다.
        // 단, 값을 변경하지 못하는 final 변수를 사용할 수 있다.
        System.out.println(a);
      }
    }

    final A obj = new A();
    obj.m1();
  }
}

