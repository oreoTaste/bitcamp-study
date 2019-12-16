package com.bitcamp.myproject;
import java.util.Scanner;

public class Test02_02 {
  final static int arrayCount = 5;
  public static void main(String[] args) {
    int[] inputNum = new int[arrayCount];
    Scanner scanner = new Scanner(System.in);
    System.out.print("입력? ");
    for(int i=0 ; i<arrayCount ; i++) {
      inputNum[i] = scanner.nextInt();
    } scanner.close();

    
    for (int i=0 ; i<arrayCount ; i++) {
      for (int j=0 ; j<i ; j++) {
        if(inputNum[i] < inputNum[j]) {
          int temp = inputNum[j];
          inputNum[j] = inputNum[i];
          inputNum[i] = temp;
          i--;
        }
      }
    }


    System.out.printf("최소값 : %d, 최대값: %d", inputNum[0] , inputNum[arrayCount-1] );
  }
}
