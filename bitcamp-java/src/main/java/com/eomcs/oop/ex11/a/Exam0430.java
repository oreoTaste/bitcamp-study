package com.eomcs.oop.ex11.a;
// anonymous class : 클래스 상속

public class Exam0430 {


  public static void main(final String[] args) {

    abstract class A {
      public abstract void print();
    }

    // 클래스를 상속받는 익명 클래스 만들기
    // 문법 : 클래스명 레퍼런스 = new 클래스명() {};


    // final A obj = () -> System.out.println("Hello");

    final A obj = new A() {
      @Override
      public void print() {
        System.out.println("Hello");
      }
    };
    obj.print();

  }
}
