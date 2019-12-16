package com.bitcamp.myproject;
import java.util.Scanner;

public class Test01_02 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("입력? ");
    int firstNum = scanner.nextInt();
    int secondNum = scanner.nextInt();
    int sum = 0;
    
    if (firstNum>secondNum) {
      int temp = firstNum;
      firstNum = secondNum;
      secondNum = temp;
    }
    scanner.close();
    
    for(int i=firstNum; i<=secondNum ; i++) {
      sum += i;
    }
    
    
    System.out.printf("%d에서 %d까지의 합은 %d입니다." , firstNum , secondNum, sum);
  }
}