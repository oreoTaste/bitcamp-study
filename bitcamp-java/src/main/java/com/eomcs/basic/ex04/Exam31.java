package com.eomcs.basic.ex04;

public class Exam31 {
    public static void main(String[] args) {
      float f = 3.141592f; //7자리면 허용
      System.out.println(f);

      float f2 = 3.1415929f; // 8자리면 에러날 확률이 높다
      System.out.println(f2);
    
      float f3 = 0.0000003141592f;
      System.out.println(f3);

      float f4 = 0.00000031415929f; // 소수점 이하인 경우 앞의 0을 빼고 7자리면 허용
      System.out.println(f4);

      float f5 = 3141592000000.0f; // 소수점 이상인 경우 0을 포함해서 유효자리 숫자 계산
      System.out.println(f5);

      double d = 9.87654321234567d; // 15자리까지 허용
      System.out.println(d);

      double d2 = 9876543212.34567d;
      System.out.println(d2);

      double d3 = 9876543212.345674d;
      System.out.println(d3);

      float ff = 99999.8877777f;
      System.out.println(ff);

      d2 = (double)99988.88f; // float -> double로 변환하는 순간 에러난다
      d3 = (double)11.1111f;
      System.out.println(d2 + d3);
    }
}
