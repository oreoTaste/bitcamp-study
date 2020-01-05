// 생성자와 인스턴스 초기화 블록(Initializer Block)
package com.eomcs.oop.ex03;

public class Exam0611 {
  static class A {
    int a;
    int b;
    int c;

    {
      a = 100;
      System.out.println("Hello!");
    }
    
    A() {
      System.out.println("A()");
      b = 200;
      c = 300;
    }
    
    A(int b) {
      System.out.println("A(int)");
      this.b = b;
      c = 300;
    }

    A(int b, int c) {
      System.out.println("A(int, int)");
      this.b = b;
      this.c = c;
    }
    
    
    { // 아무 이름 없이 선언하는 블록이 "초기화 블록"이다.
      // 인스턴스를 생성한 후, 생성자를 호출하기 전에 자동으로 실행한다.
      System.out.println("{}11111");
    }

    { // 초기화 블록은 여러 개 정의할 수 있다.
      // 순서대로 호출된다.
      System.out.println("{}22222");
      // 이렇게 초기화 블록을 나눠서 여러 개 정의하면 코드를 유지보수하기 어렵다.
      // 할 수는 있지만 이렇게 하지 말라!
      // 만약 초기화 블록을 정의한다면 한 개만 하라!
    }
  }
  public static void main(String[] args) {
    A obj1 = new A();
    System.out.printf("a=%d, b=%d, c=%d\n", obj1.a, obj1.b, obj1.c);

    A obj2 = new A(222);
    System.out.printf("a=%d, b=%d, c=%d\n", obj2.a, obj2.b, obj2.c);

    A obj3 = new A(222, 333);
    System.out.printf("a=%d, b=%d, c=%d\n", obj3.a, obj3.b, obj3.c);
}
}
