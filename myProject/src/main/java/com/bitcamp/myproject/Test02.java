package com.bitcamp.myproject;

import java.util.Scanner;

public class Test02 {
  public static void main(String[] args) {
    final int size = 5;
    int[] firstNum = new int[size];
    int sum=0;
    Scanner scanner = new Scanner (System.in);

    System.out.println("다섯값을 입력해주세요");
    System.out.print("입력? ");
    for(int i=0 ; i<size ; i++) {
      firstNum[i] = scanner.nextInt();
    }
    
    for(int i=0; i<size; i++) {
      for(int j=0 ; j<i ; j++) {
        if(firstNum[i] < firstNum[j]) {
          int temp = firstNum[i];
          firstNum[i] = firstNum[j];
          firstNum[j] = temp;
          i--;
        }
      }
    }
    System.out.printf("최소값 : %d \n최대값: %d\n", firstNum[0] , firstNum[4]);
  }
}
