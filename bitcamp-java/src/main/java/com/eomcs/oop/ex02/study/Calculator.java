package com.eomcs.oop.ex02.study;

public class Calculator {
  // 메서드가 작업한 결과를 보관하기 위해 필드 문법을 도입.
   int result = 0;
  
  void plus(int value) {
    this.result += value;
  }
  
   void minus(int value) {
     this.result -= value;
  }
  
   void multiple(int value) {
     this.result *= value;
  }
  
   void divide(int value) {
     this.result /= value;
  }  
}
