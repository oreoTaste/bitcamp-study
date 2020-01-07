package com.eomcs.oop.ex08.test;

public class J {
  public void m1() {
    Student s = new Student();
    Teacher t = new Teacher();
  }
}


abstract class Member {
  String email;
  String pwd;
  String name;
  String tel;
  
  abstract void print();
}


class Student extends Member{
  int grade;
  boolean working;

  void print() {
    System.out.println("학생정보");
  }
}


// 추상클래스(abstract class) <-> 일반클래스(concrete class)
class Teacher extends Member{
  int pay;
  String major;

  void print() {
    System.out.println("강사정보");
  }
}