// 람다(lambda) - 익명 클래스
package com.eomcs.oop.ex12;

public class Exam0111 {

  static interface Player {
    void play();
  }

  public static void main(String[] args) {
    Player p1 = new Player() {
      @Override
      public void play() {
        System.out.println("실행");
      }
    };

    p1.play();



  }
}


