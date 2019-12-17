package com.eomcs.basic.ex06.assignment;

import java.util.Scanner;

public class Test03 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("밑변 길이? ");
    final int base = scanner.nextInt();
    final int top = (base / 2);
    int blankLoop = 1;
    int wildcardLoop = 1;

    for (int i = 0 ; i < top+1 ; i++) {
      for (int j = top ; j >= blankLoop ; j--) {
        System.out.print(" ");
      } blankLoop++;
      
      for (int j = 0 ; j<wildcardLoop ; j++) {
        System.out.print("*");
        if(j>base) {return;}
      } System.out.println();
      wildcardLoop += 2;

    }
  }
}
/*

  *         2 1
 ***        1 3
*****       0 5

*/