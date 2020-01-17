// non-static nested class
package com.eomcs.oop.ex11.a;

public class Exam0320 {
  void m1() {

    class A { // Exam0320$1A.class
    }

    class B { // Exam0320$1B.class

    }

    new A();
    new B();
  }

  void m2() {
    class A { // Exam0320$2A.class
    }
    new A();
  }
}
