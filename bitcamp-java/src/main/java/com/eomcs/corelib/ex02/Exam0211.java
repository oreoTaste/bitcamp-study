// Wrapper 클래스 - 생성자와 valueOf
package com.eomcs.corelib.ex02;

public class Exam0211 {
  public static void main(String[] args) {
    Integer i1 = new Integer(3000000); // ==> int
    Integer i2 = new Integer(3000000); // ==> int

    System.out.println(i1 == i2);

    Integer i3 = Integer.valueOf(3000000);
    Integer i4 = Integer.valueOf(3000000);

    System.out.println(i3 == i4);

  }
}


