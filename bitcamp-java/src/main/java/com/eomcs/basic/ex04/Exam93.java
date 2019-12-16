package com.eomcs.basic.ex04;

// 형변환 (type casting) 정수<->정수
public class Exam93 {
  public static void main(String[] args) {
    byte b = 127;
    short s= 32767;
    int i = 21_0000_0000;
    long l = 922_0000_0000_0000_0000L;
    float f = 3.14f;
    double d = 3.14d;

    l = i;
    i = s;
    s = b;
    
    s = (short) i; // 값이 짤린다
    i = (int) l; // 값이 짤린다
    b = (byte) s; // 값이 짤린다
    
    i = 65;
    char c = (char) i;
    System.out.println(c);
    
    boolean bool;
    //bool = (boolean) b;
    //bool = (boolean) s;
    //bool = (boolean) i;
    //bool = (boolean) l;
    //bool = (boolean) f;
    //bool = (boolean) d;
    //bool = (boolean) c;
    //bool = (boolean) "true";
  }
}
