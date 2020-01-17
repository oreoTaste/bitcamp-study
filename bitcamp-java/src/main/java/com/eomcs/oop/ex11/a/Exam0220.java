// non-static nested class
package com.eomcs.oop.ex11.a;

public class Exam0220 {

  class A {
    void m1() {
    }
  }
  
  static void m1() {
    // 스태틱 멤버는 this라는 내장변수가 없기때문에 인스턴스 멤버를 사용할 수 없다.
    A obj;          // 단 레퍼런스 생성은 허용.
    // obj = new A();  // NO
  }
  
  void m2() {
    // 인스턴스 멤버는 스태틱 멤버를 사용할 수 있기 때문에 
    // 당연히 static nested class를 사용할 수 있다.
    A obj;
    obj = this.new A();
    obj = new A();
    
    obj.m1();
    Exam0220.m1();
  }

  public static void main(String[] args) {
  }
}
