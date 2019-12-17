package com.eomcs.basic.ex06.assignment;

import java.util.Scanner;

public class Test01 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner (System.in);
    System.out.print("밑변 길이? ");
    int base = scanner.nextInt();
    scanner.close();
    
    int i = 0;
    while (true) {
      i++;
      int count = 0;
      if (i == base+1) break;
      
      while (true) {
        System.out.print("*");
        count++;
        if(count == i) break;
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
*/