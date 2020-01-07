package com.eomcs.oop.ex08.test;

public class F extends Object{
  int a;
  void m1() {}
}

class F2 extends F {
  int b;
  void m2() {}
}

class F3 extends F2 {
  int c;
  void m3() {}
}

class TestF {
  public static void main(String[] args) {
    // 다형적 변수 (polymorphism)
    // 참조변수는 하위클래스의 객체 인스턴스 주소를 저장할 수 있다.
    // = 참조변수는 하위클래스 객체를 넣을 수 있다.
    
    F obj1 = new F();
    obj1 = new F2();
    obj1 = new F3();
    
    F2 obj2 = new F2();
    obj2 = new F3();
    //obj2 = new F();
    
    F3 obj3 = new F3();
    //obj3 = new F2();
    //obj3 = new F();
  }
}
