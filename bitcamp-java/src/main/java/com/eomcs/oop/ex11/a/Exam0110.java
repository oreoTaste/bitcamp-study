// non-static nested class == inner class
package com.eomcs.oop.ex11.a;

public class Exam0110 {

  // 스태틱 멤버
  static int svalue;
  static void sm() {}

  // 인스턴스 멤버
  int ivalue;
  void im() {}

  // 결론 :
  // 인스턴스 멤버를 사용하지 않는다면
  // 스태틱 클래스로 정의하라
  static class A {
    void m1() {
      svalue = 100;
      //ivalue = 200;
      sm();
      //im();
    }
  }
  
  
  public static void main(String[] args) {
    
    
  }
}
