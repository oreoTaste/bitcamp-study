// 상속 - 수퍼 클래스에 기본 생성자가 없을 때!
package com.eomcs.oop.ex05.g;

public class Exam01 {
  public static void main(String[] args) {
    B obj = new B();
    System.out.printf("v1=%d, v2=%d\n", obj.v1, obj.v2);

    B obj2 = new B();
    System.out.printf("v1=%d, v2=%d\n", obj2.v1, obj2.v2);

    B obj3 = new B();
    System.out.printf("v1=%d, v2=%d\n", obj3.v1, obj3.v2);
  }
}
