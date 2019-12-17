package com.eomcs.basic.ex06.assignment;

import java.util.Scanner;

public class Test02_01 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner (System.in);
    System.out.print("밑변 길이? ");
    int base = scanner.nextInt();
    scanner.close();
    int initialize = 0;

    for (int i=0 ; i<base ; i++) {
      for(int j=0 ; j<base ; j++) {
        System.out.print("*");
        if(j == initialize) break;
      } System.out.println(); initialize++;
    }
 
    initialize = 1;
    for (int i=0 ; i<base ; i++) {
      for(int j=base ; j>0 ; j--) {
        if(j == initialize) break;
        System.out.print("*");
      } System.out.println(); initialize++;
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
*


*/