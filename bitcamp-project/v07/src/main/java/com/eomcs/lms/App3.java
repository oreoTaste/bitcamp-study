package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    class Board {
      int no;
      String title;
      Date date;
      int viewCount;
    }
    final int SIZE = 100;

    Board[] boards = new Board[SIZE];

    String response;
    int count = 0;

    for(int i=0 ; i<100 ; i++) {
      Board lec = new Board();
      System.out.print("번호? ");
      lec.no = keyboard.nextInt();
      keyboard.nextLine(); // 줄바꿈 기호 제거용

      System.out.print("내용? ");
      lec.title = keyboard.nextLine();

      lec.date = new Date(System.currentTimeMillis());
      lec.viewCount    = 0;

      boards[i] = lec;
      count++;

      System.out.println();

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
    }

    keyboard.close();

    for (int i = 0; i < count; i++) {
      Board lec = boards[i];
      System.out.printf("%d, %s, %s, %d\n", 
          lec.no, lec.title, lec.date, lec.viewCount);
    }
  }
}








