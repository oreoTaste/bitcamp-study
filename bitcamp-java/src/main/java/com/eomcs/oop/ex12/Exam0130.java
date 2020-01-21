// 람다(lambda) - 파라미터
package com.eomcs.oop.ex12;

public class Exam0130 {

  static interface Player {
    void play(String name);
  }

  public static void main(String[] args) {

    Player p1 = (String name) -> System.out.println("와우" + name + "안녕");
    p1.play("홍길동");

    Player p2 = (name) -> System.out.println("와우" + name + "안녕");
    p2.play("홍길동");

    Player p3 = name -> System.out.println("와우" + name + "안녕");
    p3.play("홍길동");
  }
}


