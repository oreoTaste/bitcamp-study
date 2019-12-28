package com.eomcs.basic.ex04;

// 형변환 (type casting) 정수<->소수
public class Exam91 {
  public static void main(String[] args) {
    byte b = 100;
    short s = 200;
    int i = 40000;
    long l = 9_876_543_210L;
    
    float f;
    f = b;
    System.out.println(f);
    f = s;
    System.out.println(f);
    f = i;
    System.out.println(f);
    f = l;
    System.out.println(f);
    
    
    float d;
    d = b;
    System.out.println(d);
    d = s;
    System.out.println(d);
    d = i;
    System.out.println(d);
    d = l;
    System.out.println(d);
    
    
    i = (int)f;
    System.out.println(i);
    
    l = (long)f;
    System.out.println(l);
  }
}
