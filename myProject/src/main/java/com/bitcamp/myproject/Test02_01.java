package com.bitcamp.myproject;

import java.util.Scanner;

public class Test04 {
  static int inputCount=0;
  final static int size = 5;
  static int[][] firstNum = new int[20][size];
  public static void main(String[] args) {

    input();  
    arrange();
    System.out.println();
    for(int i=0 ; i<inputCount ; i++) {
      System.out.printf("최소값 : %d 최대값: %d\n", firstNum[i][0] , firstNum[i][4]);
    }
  }
  
  static void input() {
    Scanner scanner = new Scanner (System.in);
    for (int j=0 ; ; j++) {
    inputCount++;
    System.out.println("다섯값을 입력해주세요");
    System.out.print("입력? ");
      for(int i=0 ; i<size ; i++) {
        firstNum[j][i] = scanner.nextInt();
      } System.out.print("계속하시겠습니까?(y/n) ");
      scanner.nextLine();
      String repeat = scanner.nextLine();
      if(repeat.equals("Y")||repeat.equals("y")) {
        System.out.println(); continue;
      } else scanner.close(); break;
    } 
  }

  
  static void arrange() {
    for(int k=0 ; k<inputCount ; k++) {
      for(int i=0; i<size; i++) {
        for(int j=0 ; j<i ; j++) {
          if(firstNum[k][i] < firstNum[k][j]) {
            int temp = firstNum[k][i];
            firstNum[k][i] = firstNum[k][j];
            firstNum[k][j] = temp;
            i--;
          }
        }
      }
    }
  }
}
