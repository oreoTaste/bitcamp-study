// Reflection API : 중첩클래스 로딩
package com.eomcs.reflect.ex01;

public class Exam02 {
  {
    System.out.println("인스턴스 블록");
  }
  
  static {
    System.out.println("Exam02 로딩됨");
  }
  
  
  static class A {
    static int s_var = 100;
    int i_var = 200;

    static void s_m() {}
    void i_m() {}

    static {
      System.out.println("Exam02$A 클래스 로딩!");
    }
  }

  public static void main(String[] args) throws Exception {
    Class clazz = Class.forName("com.eomcs.reflect.ex01.Exam02$A");
    // 중첩 클래스는 "클래스명$중첩클래스명" 형식의 이름을 갖는다.
    // 중첩 클래스를 로딩하려면, 바깥 클래스를 알아야하기때문에 바깥 클래스도 로딩된다.
  }

}





