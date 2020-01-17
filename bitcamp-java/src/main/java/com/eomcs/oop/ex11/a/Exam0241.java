// static nested class
package com.eomcs.oop.ex11.a;

import java.util.ArrayList;
import java.util.List;

public class Exam0241 {
  public static void main(final String[] args) {
    final Musics2 m1 = new Musics2();
    m1.add("aaa.mp3");
    m1.add("bbb.mp3");
    m1.add("ccc.mp3");

    final Musics2.Player p1 = m1.createPlayer(); // factory method pattern 활용
    final Musics2.Player p2 = m1.new Player();

    p1.play();
    p2.play();

    final Musics2 m2 = new Musics2();
    m2.add("xxx.mp3");
    m2.add("yyy.mp3");
    final Musics2.Player p3 = m2.createPlayer();
    p3.play();
  }
}


class Musics2 {

  class Player {
    public void play() {
      // 이 메서드가 호출되려면, Player 객체가 존재해야함.
      // Player 객체가 존재하려면, Musics 객체가 존재해야함.
      // 따라서 이미 이 메서드가 호출될때는 이미 Musics 객체가 존재한 상태이다.
      // 그 Musics 객체로 Player객체를 만든것이다.
      // => 바깥클래스명.this 변수에 보관한다.

      for (final String song : songs) {
        System.out.println(song);
      }
      System.out.println("==============================");
    }
  }

  List<String> songs = new ArrayList<>();

  public void add(final String song) {
    songs.add(song);
  }

  public Player createPlayer() {
    return this.new Player();
  }

  public void delete(final int index) {
    songs.remove(index);
  }
}
