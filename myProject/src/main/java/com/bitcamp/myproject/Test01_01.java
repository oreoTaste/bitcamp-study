package com.bitcamp.myproject;
import java.util.Scanner;

public class Test03 {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int[] firstNum = new int[100], secondNum = new int[100];
    int[] sum = new int[100];
    int count = 0;

    for(int j=0 ; ; j++) {
      count++;
      System.out.print("입력? ");
      firstNum[j] = scanner.nextInt();
      secondNum[j] = scanner.nextInt();

      if(firstNum[j]>secondNum[j]) {
        int temp = secondNum[j];
        secondNum[j] = firstNum[j];
        firstNum[j] = temp;
      }
      
      for(int i=firstNum[j] ; i<=secondNum[j] ; i++) {
        sum[j] += i;
      } 
      
      System.out.print("계속 입력하시겠습니까?(y/n) ");
      scanner.nextLine(); // 빈칸제거
      String repeat = scanner.nextLine();
      if(repeat.equals("Y")||repeat.equals("y")){
        continue;
      } else break;

    }
    for(int j=0 ; j<count ; j++) {
      System.out.printf("=> %d에서 %d까지의 합은 %d입니다.\n", firstNum[j] ,secondNum[j], sum[j]);
    }
  }
}
