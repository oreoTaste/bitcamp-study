// 메서드 레퍼런스 : static method reference
package com.eomcs.oop.ex12;

public class Exam0520 {

  static class MyCalculator {
    public static int plus(int a, int b) {return a + b;}
    public static int minus(int a, int b) {return a - b;}
    public static int multiple(int a, int b) {return a * b;}
    public static int divide(int a, int b) {return a / b;}
  }

    static interface Calculator {
      double compute(int a, int b);
    }

    public static void main(String[] args) {
      // Calculator c1 = MyCalculator::plus;
      
      
      Calculator c1 = new Calculator() {

        @Override
        public double compute(int a, int b) {
          return (double) MyCalculator.plus(a, b);
        }
      };
      
      
      //Calculator c1 = (a, b) -> MyCalculator.plus(a, b);
      
      System.out.println(c1.compute(200, 17));

    }
}


