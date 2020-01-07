package com.eomcs.oop.ex08.test;

public class E extends Object{
  int a;
  
  // constructor
  E() {
    super();
  }
  
  E(int i) {
    this();
  }
  
  E (String s) {
    // super클래스의 생성자나 자신의 생성자 모두 첫문장에 와야하기때문에
    // 다음과 같이 나란히 놓을 수 없다.
    // super();
    // this();
  }
  
  E(int a , int b) {
    // super()를 생략하면 기본으로 수퍼클래스의 default(기본생성자)를 호출한다.
  }
}

class E2 extends E {
  int b;
  E2(int a) {
    // 생성자가 한개이상 있으면 컴파일러는 default생성자(기본생성자)를 만들지 않는다.
  }
}

class E3 extends E2 {
  int c;
  E3() {
    // 수퍼클래스에 기본 생성자가 없을때, 개발자가 직접
    // 어떤 수퍼클래스의 생성자를 호출할지 지정해야한다.
    super(100);
  }
}

class TestE {
  public static void main(String[] args) {
    E3 obj;
    obj = new E3();
    // 생성자 호출은 E3 생성자부터 상위클래스로 따라 올라가면서 호출한다.

    obj.c = 100; // D3의 변수
    obj.b = 200; // D2의 변수
    obj.a = 300; // D1의 변수

  }
}
