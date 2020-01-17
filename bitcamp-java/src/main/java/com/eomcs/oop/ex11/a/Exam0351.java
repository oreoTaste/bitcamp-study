// local class - 인스턴스 멤버에 선언된 로컬클래스
package com.eomcs.oop.ex11.a;

public class Exam0351 {

  public static void main(final String[] args) {
    final Exam0351 obj = new Exam0351();
    obj.m1();
  }

  void m1() {
    int a;
    a = 100;

    class A {
      void m1() {
        // 한번만 값을 입력한 경우 사용가능
        System.out.println(a);
      }
    }

    final A obj = new A();
    obj.m1();
  }
}

