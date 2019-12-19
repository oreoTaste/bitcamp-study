package com.eomcs.basic.ex06.assignment;

public class Test04 {
  public static void main(String[] args) {
    int age = Console.inputInt("나이? ");
    if(age >= 18) {
      System.out.println("성년입니다");
    } else {
      System.out.println("미성년입니다");
    }
  }
}
