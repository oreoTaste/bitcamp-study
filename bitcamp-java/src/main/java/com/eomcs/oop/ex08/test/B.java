package com.eomcs.oop.ex08.test;

public class B {
  // 필드
  int a; // non-static field
  String b;
  static int c;     //static field (클래스 생성시 메모리에 자동생성)
  static String d;  //= class field
  
  // 메서드
  void m1() {}
  int m2() {return 0;}
  static void m3() {} // static method (=class method)
  
  // initializer block
  {}            // instance block
  static { }    // static block
  
  // constructor
  B(){
    
  }
  
  //nested class
  static class B1{} // static nested class
  class B2{}        // non-static nested class = inner class
}
