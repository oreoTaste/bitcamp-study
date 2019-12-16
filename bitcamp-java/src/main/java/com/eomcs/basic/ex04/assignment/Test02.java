package com.eomcs.basic.ex04.assignment;

import java.util.Scanner;
import java.lang.Math;

public class Test02 {
  public static void main(String[] args) {
    java.io.InputStream inputStream = System.in;
    java.util.Scanner scanner = new java.util.Scanner(inputStream);
    
    System.out.println("가위, 바위, 보 중에 하나를 골라주세요");
    System.out.print("==> ");
    String userString = scanner.nextLine();
    int userChoice = 0;

    switch(userString) {
      case "가위" : userChoice = 0; break;
      case "바위" :userChoice = 1; break;
      case "보" :userChoice = 2; break;
        default : System.out.println("잘못 선택하셨습니다."); scanner.close(); return;
    }
    scanner.close();
    
    System.out.print("컴퓨터 : ");
    int computerChoice = (int)Math.random()*3;
    String computerString = "";
    switch (computerChoice) {
      case 0: computerString = "가위"; break;
      case 1: computerString = "바위"; break;
      case 2: computerString = "보"; break;
    } System.out.println(computerString);
    System.out.println();
    System.out.print("결과: ");
    if(userChoice == computerChoice) {
      System.out.print("비김");
    } else if(userChoice == computerChoice + 1 || userChoice == computerChoice -2 ) {
      System.out.print("이김");
    }else if(userChoice == computerChoice + 2 || userChoice == computerChoice -1 ) {
      System.out.print("짐");
    }
    
  }
}
