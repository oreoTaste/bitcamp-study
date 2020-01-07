package com.eomcs.oop.ex08.test;

import com.eomcs.oop.ex08.test.sub.H2;

public class H extends Object{
  private int a;
  int b;
  protected int c;
  public int d;
}

class H30 extends H2 {
  void m1() {
    H2 obj2 = new H2();
    //obj2.a = 100;
    //obj2.b = 200;
    //obj2.c = 300;
    obj2.d = 400;
    
    H30 obj3 = new H30();
    //obj2.a = 100;
    //obj2.b = 200;
    obj2.c = 300;
    obj2.d = 400;
    
  }
}

class TestH {
  public static void main(String[] args) {
    H obj = new H();
    //obj.a = 100;
    obj.b = 200;
    obj.c = 300;
    obj.d = 400;
  }
}
