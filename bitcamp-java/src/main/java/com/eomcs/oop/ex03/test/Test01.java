package com.eomcs.oop.ex03.test;
import com.eomcs.oop.ex03.test.sub.A;

class Score {
  static int count;
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float aver;
  
  Score() {
    System.out.println("Score()");
  }
  
  static void increase() {
    Score.count++;
  }
}

public class Test01 {
  public static void main(String[] args) {
    Score sc;
    System.out.println("---------------------");
    sc = new Score();
    Score.count = 1;
    sc.name = "홍길동";
    sc.kor = 100;
    sc.eng = 90;
    sc.math = 80;
    sc.sum = sc.kor + sc.eng + sc.math;
    sc.aver = sc.sum/3f;
    Score.increase();
    
  }
}
