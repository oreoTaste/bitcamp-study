// 산술 연산자 : 암시적 형변환과 연산우선순위
package com.eomcs.basic.ex05;

public class Exam01_6 {
    public static void main(String[] args) {
      float r1 = 5/2 + 3.1f;
      System.out.println(r1);
      float r2 = 3.1f + 5/2;
      System.out.println(r2);
      
    }
}
