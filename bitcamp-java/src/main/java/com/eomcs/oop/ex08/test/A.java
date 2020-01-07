package com.eomcs.oop.ex08.test;

class A {
  class X {

  }
  public static void main(String[] args) {
    class Y {}
    
    Object obj = new Object() {
      @Override
      public String toString() {
        return "익명클래스";
      }
    };
  }
}
