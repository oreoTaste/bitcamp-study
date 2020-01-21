// 인스턴스 메서드 레퍼런스 : instance method reference
package com.eomcs.oop.ex12;

public class Exam0620 {
  
  static class Calculator {
    double rate;
    
    public Calculator(double rate) {
      this.rate = rate;
    }
    
    public double year(int money) {return money * rate / 100;}
    public double month(int money) {return money * rate / 100 / 12;}
    public double day(int money) {return money * rate / 100 / 365;}
    public static int divide(int a, int b) {return a / b;}
  }

  static interface Interest {
    double compute(int money);
  }

  public static void main(String[] args) {
    
    // 첫번째
    Calculator 보통예금 = new Calculator(0.5);
    Interest i1 = 보통예금::year;
    
    System.out.println(i1.compute(100000));
    

    // 두번째 (위와 완전히 같다)
    Calculator 보통예금2 = new Calculator(0.5);
    Interest i2 = new Interest() {
      @Override
      public double compute(int money) {
        return 보통예금2.year(money);
      }
    };

    System.out.println(i2.compute(100000));

    
  }
}


