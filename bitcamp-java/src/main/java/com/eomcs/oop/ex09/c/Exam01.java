// 서브 인터페이스 구현 - 수퍼 인터페이스의 메서드까지 모두 구현해야 한다.
package com.eomcs.oop.ex09.c;

public class Exam01 implements B {
  // 인터페이스에서 선언된 메서드 구현
  public void m2() {System.out.println("Exam01의 m2");} // B 인터페이스 뿐만아니라,
  public void m1() {System.out.println("Exam01의 m1");} // B의 수퍼인터페이스의 메서드까지 구현해야 한다.
  
  
  // 클래스에서 추가된 메서드
  public void x1() {}
  public void x2() {}
  
  public static void main(String[] args) {
    Exam01 obj = new Exam01();
    
    obj.m1();
    obj.m2();
    obj.x1();
    obj.x2();
    
    System.out.println("============================");
    
    B obj2 = obj;
    obj2.m1(); // B의 메서드가 먼저 나온다.
    obj2.m2(); // B의 메서드가 먼저 나온다.
    //obj2.x1(); 컴파일 오류
    //obj2.x2(); 컴파일 오류
  }
}
