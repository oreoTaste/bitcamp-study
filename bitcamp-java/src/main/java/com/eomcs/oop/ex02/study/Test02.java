package com.eomcs.oop.ex02.study;

public class Test02 {
  public static void main(String[] args) {
    Calculator cal1 = new Calculator();
    Calculator cal2 = new Calculator();
    
    cal1.plus(2);
    cal1.plus(3);
    cal1.minus(1);
    cal1.multiple(7);
    cal1.divide(3);
    
    System.out.printf("결과값: %d\n", cal1.result);
    
  }
  

}
