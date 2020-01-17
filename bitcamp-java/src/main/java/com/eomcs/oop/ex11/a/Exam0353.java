// local class - 인스턴스 멤버에 선언된 로컬클래스
package com.eomcs.oop.ex11.a;

public class Exam0353 {

  public static void main(final String[] args) {
    final Exam0353 obj = new Exam0353();
    obj.m1();
  }

  void m1() {
    int a;
    a = 100;

    class A {
      void m1() {
        // 로컬 클래스에서 바깥매서드의 로컬 변수를 사용할 때는
        // fianl 변수로 간주하기 때문에 값을 변경할 수 없다.

        // a = 200; // 컴파일 오류

        // 즉, 로컬클래스에서 바깥메서드 로컬변수를 사용할 경우에는
        // 단지 값을 조회하는 용도로 사용하는 것이다.
      }
    }

    final A obj = new A();
    obj.m1();
  }
}

