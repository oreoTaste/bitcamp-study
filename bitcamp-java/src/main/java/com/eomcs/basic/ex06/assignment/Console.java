package com.eomcs.basic.ex06.assignment;

import java.util.Scanner;

public class Console {
  static int inputInt(String msg) {
    java.io.InputStream inputStream = System.in;
    Scanner scanner = new Scanner (inputStream);
    System.out.print(msg);
    int base = scanner.nextInt();
    //scanner.close();
    return base;
  }

  @Deprecated
  static int inputInt() {
    Scanner scanner = new Scanner (System.in);
    System.out.print("밑변 길이? ");
    int base = scanner.nextInt();
    scanner.close();
    return (base & 0x01)==0x01 ? base-1 : base;
  }
}
