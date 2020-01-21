// 람다(lambda)
package com.eomcs.oop.ex12;

public class Exam0311 {

  static interface Player {
    void play();
  }

  static void testPlayer(Player p) {
    p.play();
  }

  public static void main(String[] args) {

    Player p1 = () -> System.out.println("입력");

    testPlayer(p1);



  }
}


