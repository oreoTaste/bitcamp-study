package com.eomcs.basic.ex04;

// 변수의 종류
public class Exam71 {
  int instanceVar; //인스턴스 필드
  // => new 명령을 실행할때 준비되는 변수
  static int classVar; //클래스 필드(클래스필드) = 스태틱필드
  // => 클래스가 로딩될때 준비

  public static void main(String[] args/*이것도 로컬변수이면서, 파라미터*/) {
    int local; //로컬변수
    int local2;//로컬변수
    // => 매서드를 실행할때 준비되는 변수
  }
  
  //클래스메서드(스태틱메서드)
  static void m1() {
    int local; //로컬변수
    // => 매서드를 실행할때 준비되는 변수
    // => 다른 메서드 블럭에 있는 변수를 사용할 수 없다.

    
    classVar = 100;
    //클래스필드는 어디서든 사용가능(=static멤버는 사용할 수 있다)
    
    //instanceVar = 200;
    //인스턴스 변수는 사용할 수 없다.
  }
  
  //인스턴스 메서드
  void m2() {
    int local; // ok
    classVar = 200; // 클래스필드는 어디서든 사용가능
    instanceVar = 200; // 인스턴스변수도 사용가능.(static이 안붙은 메서드에서)
  }
}
// 70*6 + 400 = 820