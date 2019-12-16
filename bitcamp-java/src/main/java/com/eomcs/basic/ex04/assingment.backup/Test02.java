package com.eomcs.basic.ex04.assignment;

import java.util.Scanner;
import java.lang.Math;

public class Test02 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    //입력받기 (String으로 받은 후, int로 변환)
    System.out.println("묵, 찌, 빠 중에서 하나를 골라주세요");
    System.out.print("  - 나의 선택:\t");
    String userChoice = scanner.nextLine();
    int userChoiceNum = 0;
    switch(userChoice) {
      case "묵" : userChoiceNum = 1; break;
      case "찌" : userChoiceNum = 2; break;
      case "빠" : userChoiceNum = 3; break;
      default : System.out.println("*******잘못 선택하셨습니다.*******");break;
    } scanner.close();
    
    // 컴퓨터의 랜덤 값 출력
    System.out.print("  - 컴퓨터의 선택:\t");
    int computerChoiceNum = (int)Math.floor(Math.random()*3)+1;
    switch(computerChoiceNum) {
      case 1: System.out.println("묵"); break;
      case 2: System.out.println("찌"); break;
      case 3: System.out.println("빠"); break;
    }
    System.out.print("  =====>\t");
    
    // 결론 도출
    if(userChoiceNum==0) System.out.println("잘못 선택으로 인한 패배.");
    if(userChoiceNum == computerChoiceNum) {
      System.out.println("비김");
    } else if(userChoiceNum == computerChoiceNum +1 || userChoiceNum == computerChoiceNum -2) {
      System.out.println("짐");
    } else if(userChoiceNum == computerChoiceNum +2 || userChoiceNum == computerChoiceNum -1) {
      System.out.println("이김");
    }
    
  }
}
