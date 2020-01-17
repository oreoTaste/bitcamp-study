// non-static nested class
package com.eomcs.oop.ex11.a;

public class Exam0210 {

  void m2() {
    System.out.println("Exam0210클래스의 m2입니다.");
  }
  
  // 스태틱 멤버
  static int sValue;
  static void sm() {}

  // 인스턴스 멤버
  int iValue;
  void im() {}

  // non-static nested class 는 인스턴스 멤버이다.
  // 따라서 인스턴스가 있어야만 사용할 수 있다.
  class A {
    void m1() {
      
      // 인스턴스 멤버는 스태틱 멤버를 자유롭게 사용할 수 있다.
      sValue = 100; // OK
      // 인스턴스 멤버는 같은 인스턴스 멤버를 자유롭게 사용할 수 있다.
      iValue = 100;
      Exam0210.this.iValue = 100; // OK
      
      
      // 인스턴스 멤버는 스태틱 멤버를 자유롭게 사용할 수 있다.
      sm(); // OK
      // 인스턴스 멤버는 같은 인스턴스 멤버를 자유롭게 사용할 수 있다.
      Exam0210.this.im(); // OK
      
      Exam0210.this.m2();
      m2();

     
      // innerclass에 같은 이름의 멤버가 없다면
      // 바깥클래스명.this를 생략해도 된다.
    }
    
    void m2() {
      System.out.println("innerclass A의 m2입니다");
    }
    
  }
  
  // 결론:
  // 인스턴스 멤버를 사용하지 않는다면 
  // static nested class 로 정의하라!

  public static void main(String[] args) {
    Exam0210 a = new Exam0210();
    a.aa();
  }
  
  void aa() {
    Exam0210.A a;
    a = new Exam0210.A();
    a.m1();
    
  }
}
