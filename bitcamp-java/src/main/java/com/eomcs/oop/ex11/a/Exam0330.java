// local class - 로컬 클래스와 로컬변수
package com.eomcs.oop.ex11.a;

public class Exam0330 {

  public static void main(final String[] args) {
    // static 멤버는 인스턴스없이 사용할 수 있다.
    // => 즉, 다음과 같이 sm()을 호출할때 Exam0310 객체없이 호출한다면
    // sm() 메서드 안에는 절대로 Exam0310객체를 사용할 수 없는것이다.
    Exam0330.sm();
    final Exam0330 e = new Exam0330();

  }

  // 스태틱멤버 (스태틱메서드)의 로컬클래스 사용법
  static void sm() {
    class A {


      void m1() {
        // 로컬 클래스가 스태틱멤버에 정의된 경우
        // 바깥 클래스의 인스턴스를 모르기 때문에
        // 바깥 클래스의 인스턴스 멤버를 사용할 수 없다.
        // Exam0330.this.iValue = 100; // 컴파일 오류
      }
    }

    final A obj = new A();
    obj.m1();
  }

  int iValue;

}
