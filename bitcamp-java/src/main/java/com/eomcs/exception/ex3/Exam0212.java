// 예외 던지기 - 예외 상황을 호출자에게 알려주기
package com.eomcs.exception.ex3;

import java.io.FileNotFoundException;

public class Exam0212 {

  // 보통 개발자가 애플리케이션을 작성하면서
  // 예외를 던질 경우, 이 클래스 및 하위클래스를 사용한다.
  static void m1() throws Exception, FileNotFoundException {
    int a = 100;
    if(a > 0)
      throw new FileNotFoundException();
    else
      throw new Exception(); // OK! 보통 개발자가 사용하는 예외 클래스이다.
  }

  static void m2() throws Exception {
    // throw new String(); // 컴파일 오류!
    // throw 로 던질 수 있는 객체는 오직 java.lang.Throwable 타입만 가능하다.
  }

  public static void main(String[] args) {
  }

}
