package com.eomcs.basic.ex07.test;

public class Test01 {
  public static void main(String[] args) {
    m1();
    m2("홍길동");
    System.out.println(m3());
    System.out.println(m4("홍길동"));
    
  }

  static void m1 () {
    System.out.println("Hello");
  }

  static void m2 (String name) {
    System.out.println(name);
  }

  static String m3 () {
    String msg = "Hello";
    return msg;
  }

  static String m4(String name) {
    String msg = String.format("%s님 안녕하세요!", name);
    String.format("%s 와우 신난다\n", name);
    return msg;
  }

}
