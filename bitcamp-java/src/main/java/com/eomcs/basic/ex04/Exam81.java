package com.eomcs.basic.ex04;

// 변수와 블럭
public class Exam81 {
  int a = 100; //인스턴스필드
  static int b = 200;//스태틱필드(클래스필드)
  final int c = 300;
  
  public static void main(String[] args/*이것도 로컬변수이면서, 파라미터*/) {
    int a;
    // 메서드 안에 블럭을 이용하여 변수의 사용번위를 통제할 수 있다
    {
      //블록안에서는 블록밖의 변수를 사용할 수 있다.
      int b=200;
      //블록안의 변수를 사용할때는 블록을 실행할때 준비된다.(블럭을 나가면 자동으로 제거된다)
    }
    {
      
    }
  }
}
