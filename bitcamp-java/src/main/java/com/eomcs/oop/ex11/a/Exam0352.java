// local class - 인스턴스 멤버에 선언된 로컬클래스
package com.eomcs.oop.ex11.a;

public class Exam0352 {

  public static void main(final String[] args) {
    final Exam0352 obj = new Exam0352();
    obj.m1();
  }

  void m1() {
    int a;
    a = 100;
    a = 200;

    class A {
      void m1() {
        // 로컬 변수의 값이 두번이상 바뀌는 경우, 상수값으로 취급할 수 없기때문에
        // 로컬 클래스에서 메서드의 로컬변수를 사용할 수 없다.

        // System.out.println(a);

        // 결론!
        // 로컬클래스는 메서드의 final 변수를 사용할 수 있다.
        // 값이 변경될 수 있는 일반 변수는 사용할 수 없다.
      }
    }

    final A obj = new A();
    obj.m1();
  }
}

