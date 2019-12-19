package com.eomcs.oop.test;

import java.util.ArrayList;

public class Test05 {
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
    System.out.println(m1);
    
    // m1을 m2에 복사한게 아니라
    // m2는 m1의 주소값을 받은 것이다.
    Member m2 = m1;
    System.out.println(m2);
  }
}
