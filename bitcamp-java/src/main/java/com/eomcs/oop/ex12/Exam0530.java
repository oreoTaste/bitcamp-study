// 메서드 레퍼런스 : static method reference
package com.eomcs.oop.ex12;

public class Exam0530 {
  static class MyCalculator {
    public static int plus(int a, int b) {return a + b;}
    public static int minus(int a, int b) {return a - b;}
    public static int multiple(int a, int b) {return a * b;}
    public static int divide(int a, int b) {return a / b;}
  }

  static interface Calculator {int compute(int a, int b);}
  static interface Calculator1 {double compute(int a, int b);}
  static interface Calculator2 {float compute(int a, int b);}
  static interface Calculator3 {short compute(int a, int b);}
  static interface Calculator4 {void compute(int a, int b);}
  static interface Calculator5 {Object compute(int a, int b);}
  static interface Calculator6 {String compute(int a, int b);}


  public static void main(String[] args) {
    // int -> double
    Calculator1 c1 = MyCalculator::plus;

    // int -> float
    Calculator2 c2 = MyCalculator::plus;

    // int -> short
    // Calculator3 c3 = MyCalculator::plus;

    // int -> void (리턴값은 무시되고 진행)
    Calculator4 c4 = MyCalculator::plus;
    c4.compute(100, 200);

    // int -> Object
    Calculator5 c5 = MyCalculator::plus;

    // int -> String
    //Calculator6 c6 = MyCalculator::plus;


  }
}


