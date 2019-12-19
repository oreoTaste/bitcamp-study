package com.eomcs.oop.test;

public class Test06 {
  public static void main(String[] args) {
    class Member {
      int no;
      String name;
      int birthYear;
      char gender;
      float height;
      float weight;
      boolean personalTraining;
    }

    Member[] m;
    m = new Member[100]; // Member 레퍼런스 생성

    
    for(int i=0 ; i<100 ; i++) {
      m[i] = new Member(); // Member 인스턴스 생성
    }
    m[0].no = 100;
    m[1].no = 200;
    System.out.println(m[1].no);
  }
}
