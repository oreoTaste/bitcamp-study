package com.eomcs.oop.ex09.g;

public interface A {
  /* public abstract */ void m1();

  default void m2() {
    System.out.println("A의 m2입니다");
  }
}
