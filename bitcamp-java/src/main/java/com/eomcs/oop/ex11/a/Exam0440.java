package com.eomcs.oop.ex11.a;
// anonymous class : 수퍼클래스의 생성자 지정하기

public class Exam0440 {


  public static void main(final String[] args) {

    class A {
      String name;

      public A() {
        name = "이름없음";
      }

      public A(final String name) {
        this.name = name;
      }

      public void print() {
        System.out.println(name);
      }
    }

    // 익명 클래스를 정의할때 super클래스의 생성자를 지정할 수 있다.

    final A obj = new A("젠장") {};
    obj.print();

    final A obj2 = new A() {};
    obj2.print();


  }
}
