// 람다(lambda) - 파라미터
package com.eomcs.oop.ex12;

public class Exam0150 {

  static interface Calculator {
    int compute(int a, int b);
  }

  public static void main(String[] args) {

    Calculator c = (a, b) -> {
      return a + b;
    };

    System.out.println(c.compute(200, 100));

    Calculator c2 = (a, b) -> a + b;
    System.out.println(c2.compute(100, 100));

    // 리턴값이 있는 경우, 반드시 표현식(Expression)이 나와야한다.
    // Expression? 실행한후 결과가 리턴되는 명령.
  }
}


