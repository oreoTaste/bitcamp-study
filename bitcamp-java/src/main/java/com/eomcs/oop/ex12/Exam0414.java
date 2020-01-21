// 람다(lambda)
package com.eomcs.oop.ex12;

public class Exam0414 {

  static interface Interest {
    double compute(int deposit);
  }

  static Interest getInterest(final double rate) {
    return deposit -> deposit + (deposit) * rate / 100;
  }

  public static void main(String[] args) {
    Interest i1 = getInterest(1.5);
    System.out.printf("합계 : %.1f\n", i1.compute(1_000_000_000));

    Interest i2 = getInterest(2.5);
    System.out.printf("합계 : %.1f\n", i2.compute(1_000_000_000));


  }
}


