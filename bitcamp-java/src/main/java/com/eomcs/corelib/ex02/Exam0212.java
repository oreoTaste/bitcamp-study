// Wrapper 클래스 - 생성자와 valueOf
package com.eomcs.corelib.ex02;

public class Exam0212 {
  public static void main(String[] args) {
    Integer i1 = new Integer(3000000); // ==> int
    Integer i2 = new Integer(3000000); // ==> int
    System.out.println(i1 == i2);
    System.out.println("equals:" + i1.equals(i2));

    System.out.println("====");

    Integer x1 = 3000000;
    Integer x2 = 3000000;
    System.out.println(x1 == x2);
    System.out.println("equals:" + x1.equals(x2));

    System.out.println("====");


    Integer i3 = Integer.valueOf(127);
    Integer i4 = Integer.valueOf(127);
    System.out.println(i3 == i4);
    System.out.println("equals:" + i3.equals(i4));

    System.out.println("====");

    Integer i5 = Integer.valueOf(128);
    Integer i6 = Integer.valueOf(128);
    System.out.println(i5 == i6);
    System.out.println("equals:" + i5.equals(i6));


  }
}


