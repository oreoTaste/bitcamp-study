// Reflection API : 클래스 로딩과 인스턴스 생성
package com.eomcs.reflect.ex01;


public class Exam05 {
  static {
    System.out.println("Exam05 클래스 생성됨");
  }
  
  static class A {
    static {
      System.out.println("Exam05$A 클래스 생성됨");
    }
    
    {
      System.out.println("Exam05$A 인스턴스 클래스 생성됨");
    }

    void m() {
      System.out.println("Hello!");
    }

  }

  public static void main(String[] args) throws Exception {
    Class clazz = Class.forName("com.eomcs.reflect.ex01.Exam05$A");

    System.out.println("========================================");
    // 타입(클래스) 정보만 있다면 인스턴스 생성할 수 있다.
    A obj = (A) clazz.newInstance();
    obj.m();
  }

}





