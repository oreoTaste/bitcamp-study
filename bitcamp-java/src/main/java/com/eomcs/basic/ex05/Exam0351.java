package com.eomcs.basic.ex05;

//# 비트 연산자(&)를 이용하여 % 연산자 구현하기
//
public class Exam0351 {
  public static void main(String[] args) {
    System.out.println(57 % 2);
    System.out.println(57 & 0b1);
    
    System.out.println(57 % 4);
    System.out.println(57 & 0b11);
    
    System.out.println(57 % 8);
    System.out.println(57 & 0b111);

    System.out.println(57 % 16);
    System.out.println(57 & 0b1111);
  }
}
