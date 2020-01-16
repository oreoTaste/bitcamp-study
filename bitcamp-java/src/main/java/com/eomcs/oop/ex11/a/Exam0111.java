// non-static nested class == inner class
package com.eomcs.oop.ex11.a;

public class Exam0111 {

  // 스태틱 멤버
  static int svalue;
  static void sm() {}

  // 인스턴스 멤버
  int ivalue;
  void im() {}

  // 결론 :
  // 인스턴스 멤버를 사용하지 않는다면
  // 스태틱 클래스로!
  static class A {
    void m1() {
      svalue = 100;
      //ivalue = 200;
      sm();
      //im();
    }
  }
  
  static void m1() {
    // static nested class 는 스태틱 멤버이기 때문에
    // 다른 스테틱 멤버가 사용할 수 있다.
    A obj;
    obj = new A();
  }
  
  void m2() {
    // 인스턴스 멤버는 스태틱멤버를 사용할 수 있기 때문에
    // 당연히 static nested class를 사용할 수 있다.
    A obj;
    obj = new A();
  }

  
  public static void main(String[] args) {
    
    
  }
}
