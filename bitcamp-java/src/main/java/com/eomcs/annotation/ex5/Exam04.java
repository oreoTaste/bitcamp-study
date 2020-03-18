// 애노테이션 프로퍼티 값 추출 - 배열 값 추출
package com.eomcs.annotation.ex5;

public class Exam04 {

  public static void main(String[] args) {
    Class<?> clazz = MyClass4.class;
    MyAnnotation3 obj = clazz.getAnnotation(MyAnnotation3.class);

    for(int i = 0; i < obj.v1().length; i++) {
      System.out.println(obj.v1()[i]);
    }
    for(int i = 0; i < obj.v2().length; i++) {
      System.out.println(obj.v2()[i]);
    }
    for(int i = 0; i < obj.v3().length; i++) {
      System.out.println(obj.v3()[i]);
    }

  }
}




