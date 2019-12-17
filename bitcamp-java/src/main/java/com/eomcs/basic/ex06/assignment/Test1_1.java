package com.eomcs.basic.ex06.assignment;

import java.util.Scanner;

public class Test1_1 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner (System.in);
    System.out.print("밑변 길이? ");
    int base = scanner.nextInt();
    scanner.close();

    int count = 0;
    for (;;) {
      for(int j=0 ; j<count ; j++) {
        if (j == base) return;
        System.out.print("*");
      } System.out.println(); count++;
    }

  }
}
/*
 *
 **
 ***
 ****
 *****
 */