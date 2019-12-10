package com.eomcs.basic.ex03;

public class Exam1 {
  public static void main(String[] args){
    //정수 리터럴 (10진수)
    System.out.println(78);
    //정수 리터럴 (8진수)
    System.out.println(0116);
    System.out.println(-0116);
.3
    //정수 리터럴 (16진수)
    System.out.println(0x4e);
    System.out.println(-0x4e);

    //정수 리터럴 (2진수)
    System.out.println(0b01001110);
    System.out.println(-0b01001110);


    //부동소수 리터럴 (e는 곱하기 10과 같다..)
    System.out.println(3.14);
    System.out.println(0.314e1);
    System.out.println(31.4e-1);

    //논리 리터럴
    System.out.println(true);
    System.out.println(false);

    //문자 리터럴
    System.out.println('a'); // 한글자 표시할때
    System.out.println("wow"); //여러글자를 표시할때

    
  }
}