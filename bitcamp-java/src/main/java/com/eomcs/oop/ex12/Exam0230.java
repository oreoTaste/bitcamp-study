// 람다(lambda)
package com.eomcs.oop.ex12;

public class Exam0230 {

  static interface Player {
    void play();

    // 람다는 수식이 2개이상이면 사용할 수 없다.
    // 즉, 추상메서드가 1개짜리인 인터페이스만 가능하다.
    // void stop();
  }

  public static void main(String[] args) {
    Player p1 = () -> System.out.println("Hello");
    p1.play();



  }
}


