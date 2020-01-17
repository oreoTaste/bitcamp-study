// static nested class
package com.eomcs.oop.ex11.a;

public class Exam0231 {
  public static void main(final String[] args) {
    final Exam0231_X exam0230_x = new Exam0231_X();

    exam0230_x.new A("홍길동", 20);
  }
}


class Exam0231_X {
  class A {
    String name;
    int age;

    public A(final String name, final int age) {
      this.name = name;
      this.age = age;
    }

    void m1() {
      System.out.println("A의 m1");
    }
  }
}
