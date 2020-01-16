// non-static nested class == inner class
package com.eomcs.oop.ex11.a;

import static com.eomcs.oop.ex11.a.Exam0112_X.*;

public class Exam0113 {
  public static void main(String[] args) {
    // import static을 이용하면 패키지 멤버를 그냥 사용하는 것과 
    // 똑같은 방법으로 클래스 이름을 생략 할 수 있다.
    sValue = 100;
    
    // static nested class 또한 같은 방법으로 사용할 수 있다.
    A obj;
    obj = new A();
  }
}
