package com.eomcs.oop.test;

public class Test03 {
  public static void main(String[] args) {
    
    //회원정보를 담을 수 있는 메모리의 설계도
    class Member {
      int no;
      String name;
      int birthYear;
      char gender;
      float height;
      float weight;
      boolean personalTraining;
    }
    
    
    Member m1;
    m1 = new Member();
    m1.no = 100;
    m1.name = "홍길동";
    m1.birthYear = 2001;
    m1.height = 180.5f;
    m1.personalTraining = true;
  }
}
