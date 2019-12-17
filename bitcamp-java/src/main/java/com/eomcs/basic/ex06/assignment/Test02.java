package com.eomcs.basic.ex06.assignment;

import java.util.Scanner;

public class Test02 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner (System.in);
    System.out.print("밑변 길이? ");
    int base = scanner.nextInt();

    int i = 0;
    while (i < base) {
      i++;
      int count = 0;
      while (true) {
        System.out.print("*");
        count++;
        if(count == i) break;
      } System.out.println();
    }
    
    int j = 0;
    while(true) {
      j++;
      int count = base;
      if(j == base) break;
      while(true) {
        count--;
        System.out.print("*");
        if(count == j) break;
      } System.out.println();
    }
  }
}
/*

*
**
***
****
*****
****
***
**

*/