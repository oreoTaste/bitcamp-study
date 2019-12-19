package com.eomcs.oop.test;

public class Test02 {
  public static void main(String[] args) {

    //다양한 종류의 데이터를 담을 수 있는 메모리를 설계하는 문법을 제공한다.
    // -> "사용자 정의 데이터타입"
    // (user defined data type)
    // -> 초기화 방법 : new 명령
    // -> 이때 생성된 메모리를 인스턴스(instance)라고 한다.
    // 일반적으로 객체(Object)라고도 한다.
    // 이때 인스턴스(=객체, 메모리)의 주소를 저장하는것이 레퍼런스(Reference)라고 한다.
    
    class Member {
      int no;
      String name;
      int birthYear;
      char gender;
      float height;
      float weight;
      boolean personalTraining;
    }
    
    
    Member m1 = new Member();
    m1.no = 100;
    m1.name = "홍길동";
    m1.birthYear = 2001;
    m1.height = 180.5f;
    m1.personalTraining = true;
  }
}
