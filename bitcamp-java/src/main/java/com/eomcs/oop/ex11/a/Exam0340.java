// local class - 인스턴스 멤버에 선언된 로컬클래스
package com.eomcs.oop.ex11.a;

public class Exam0340 {

  public static void main(final String[] args) {
    // 인스턴스 멤버는 반드시 인스턴스 생성후에 사용할 수 있다.
    final Exam0340 obj = new Exam0340();
    obj.im(); // im()을 호출할때 Exam0340을 넘겨준다.
    // 그러면 im()에서는 this라는 내장변수에 보관할 것이다.

  }

  int iValue;

  // 인스턴스 메서드의 로컬클래스 사용법
  void im() {
    class A {
      void m1() {
        // 로컬 클래스가 인스턴스 멤버에 정의된 경우
        // 바깥 클래스의 인스턴스 멤버를 사용할 수 있다.
        // Exam0340.this.iValue = 100; OK
        iValue = 100;
      }
    }

    final A obj = new A();
    obj.m1();
  }

}
