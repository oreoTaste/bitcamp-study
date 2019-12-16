package com.eomcs.basic.ex04.assignment;

import java.util.Scanner;
import java.lang.Math;

public class Test03 {
  public static void main(String[] args) {
    //입력부분
    Scanner scanner = new Scanner(System.in);
    System.out.println("============================================");
    System.out.println("=================랜덤 로또 추첨기=================");
    System.out.println("============================================");
    System.out.println("* 공에 적힌 번호는 최대 몇번까지 있나요?(1번부터 시작한다고 가정)");
    System.out.print("=>  ");
    int maxNum = scanner.nextInt();
    System.out.println("* 공은 몇개까지 뽑나요?(1개 이상 뽑는다고 가정)");
    System.out.print("=>  ");
    int numOfBalls= scanner.nextInt();
    scanner.close();
    
    //추첨부분
    int[] result = new int[100];
        
    for(int i=0 ; i<numOfBalls ; i++){
      result[i] = 1 + (int) Math.floor(Math.random()*maxNum);
      for(int j=0 ; j<i ; j++) {
        if(result[i] == result[j]) {
          i--;
          continue;
        }
      }
    }
    
    System.out.print("추첨값 : ");
    for(int i=0 ; i<numOfBalls ; i++) {
    System.out.printf("%d   ", result[i]);
    }
    
  }
}
