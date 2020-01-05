package com.eomcs.oop.ex06.d;

public class Member {
  String name;
  int age;

  public Member(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public boolean equals(Object obj) {
    Member m = (Member)obj;
    if(m.getClass() != Member.class) return false;
    if(!m.name.equals(this.name)) return false;
    if(m.age != this.age) return false;
    return true;
  }
}
