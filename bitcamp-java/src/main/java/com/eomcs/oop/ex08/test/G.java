package com.eomcs.oop.ex08.test;

public class G extends Object{
  int a;
  void m1() {}
}


class G2 extends G {
  int b;
  
  @Override
  void m1() {       // 오버라이딩
    this.b = 100;
  }
  
  void m1(int i) {  // 오버로딩
    this.b = i;
  }
  
  void m2() {}
}


class H3 extends G2 {
  int c;
  void m3() {}
  
  @Override
  void m1() {}
}


class TestG {
  public static void main(String[] args) {
    H3 obj1 = new H3();
    obj1.m1();          //G3의 m1을 호출
    
    G obj2 = new H3();
    obj2.m1();          //G3의 m1을 호출
    
    G obj3= new G();
    obj3.m1();          //G의 m1을 호출
  }
}
