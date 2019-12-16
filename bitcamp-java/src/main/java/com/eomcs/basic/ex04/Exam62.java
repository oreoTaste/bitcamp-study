package com.eomcs.basic.ex04;

//상수
public class Exam62 {
  public static void main(String[] args) {
    int a;
    a = 100;
    a = 200;
    // 값을 한번만 저장하고 안바꾸고 싶을때
    final int b;
    b = 100;
    //b = 200; -> 컴파일오류
    final int c = 200;
    //c = 100; 컴파일오류
  }
}
