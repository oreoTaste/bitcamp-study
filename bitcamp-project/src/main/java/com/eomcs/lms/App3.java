package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

// 고급
// 1) 배열의 개수를 변수에 -저장하여 크기 변경을 쉽게 하기
// 2) 배열의 개수를 저장하고 있는 변수를 함부로 변경하지 못하도록 한다.
//    final
// 3) 코드를 관리하기 쉽도록 작은 기능 단위로 분리한다.
//    메서드 
// 4) 메서드 사이에 공유하는 변수는 클래스 변수로 선언한다.
//    스태틱 변수
// 5) 복합 데이터를 저장할 메모리를 설계한다.
//    클래스
public class App3 {
  
  
  static final int size = 5734;
  
  static Board[] boards = new Board[size];
  
  static int[] viewCount = new int[size];
  
  static int count = 0;
  

  public static void main(String[] args) {
    
    inputBoards();

    System.out.println();

    printBoards();
  }
  
  static void inputBoards() {
    Scanner keyboard = new Scanner(System.in);
    String response;
    
    for (int i = 0; i < size; i++) {
      Board b = new Board(); // Board 설계도에 따라 메모리 준비해서 리턴한다.
      
      System.out.print("번호? ");
      b.no = keyboard.nextInt();
      keyboard.nextLine(); // 줄바꿈 기호 제거용

      System.out.print("내용? ");
      b.title = keyboard.nextLine();

      b.date = new Date(System.currentTimeMillis());
      b.viewCount    = 0;
      
      boards[i] = b;
      
      count++;
      
      System.out.println();

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
    }
    
    keyboard.close();
  }
  
  static void printBoards() {
    for (int i = 0; i < count; i++) {
      Board b = boards[i];
      System.out.printf("%d, %s, %s, %d\n", 
          b.no, b.title, b.date, b.viewCount);
    }
  }
}








