package com.eomcs.basic.ex99;

import java.util.Scanner;

public class Exam22 {
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    java.util.Scanner scanner = new java.util.Scanner(keyboard); //스캐너는 샤워기헤드다


    System.out.print("입력>");
    int i1 = scanner.nextInt();
    int i2 = scanner.nextInt();
    int i3 = scanner.nextInt();
    scanner.close();
    System.out.println("------------------------------------------------");
    System.out.println(i1);
    System.out.println(i2);
    System.out.println(i3);
    // space, tab, enter를 만나면 int값을 찾아서 리턴한다.

  }
}
