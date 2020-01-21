// 람다(lambda) - body
package com.eomcs.oop.ex12;

public class Exam0120 {

  static interface Player {
    void play();
  }

  public static void main(String[] args) {
    // 한문장일 때
    Player p1 = () -> System.out.println("");
    p1.play();

    Player p2 = () -> {
      System.out.println("");
      System.out.println("");
    };
    p2.play();


  }
}


