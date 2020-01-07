package com.eomcs.oop.ex08.test;

public class D extends Object{
  int a;
}

class D2 extends D {
  int b;
}

class D3 extends D2 {
  int c;
}

class TestD {
  public static void main(String[] args) {
    D3 obj = new D3(); // D3가 상속하는 모든 클래스의 인스턴스필드까지 생성한다.
    obj.c = 100; // D3의 변수
    obj.b = 200; // D2의 변수
    obj.a = 300; // D1의 변수

  }
}
