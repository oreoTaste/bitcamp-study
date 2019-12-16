package com.bitcamp.myproject;

import java.util.Scanner;

public class Test01 {
  public static void main(String[] args) {
    int firstNum;
    int secondNum;
    int sum=0;

    Scanner scanner = new Scanner (System.in);

    System.out.println("두 값을 입력해주세요");
    System.out.print("입력? ");
    firstNum = scanner.nextInt();
    secondNum = scanner.nextInt();

    if(firstNum>secondNum) {
      int temp = secondNum;
      secondNum = firstNum;
      firstNum = temp;
    } else {
      sum = firstNum;
      for(int i=firstNum+1 ; i<=secondNum ; i++) {
        sum = sum+i;
      } System.out.printf("==> %d에서 %d까지의 합은 %d 입니다. ", firstNum , secondNum,sum);
    }
  }
}