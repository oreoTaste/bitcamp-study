// 람다(lambda) - 로컬 클래스
package com.eomcs.oop.ex12;

public class Exam0113 {

  static interface Player {
    void play();
  }

  public static void main(String[] args) {
    Player p1 = () -> System.out.println("");

    p1.play();



  }
}


