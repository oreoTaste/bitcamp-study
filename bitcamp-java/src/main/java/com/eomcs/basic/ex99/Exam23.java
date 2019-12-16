package com.eomcs.basic.ex99;
import java.util.Scanner;

public class Exam23 {
  public static void main(String[] args) {
    //java.io.InputStream keyboard = System.in;
    //java.util.Scanner scanner = new java.util.Scanner(keyboard); //스캐너는 샤워기헤드다
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    System.out.print("입력>");
    int i1 = scanner.nextInt();
    String i2 = scanner.nextLine();
    String s = scanner.nextLine();
    scanner.close();
    System.out.println("------------------------------------------------");
    System.out.println(i1);
    System.out.println(i2);
    System.out.println(s);
    // nextInt()의 문제점? 알아야할점
    // white space (space, tab, new line기호)를 만나면 그 앞까지 받은 값을 int로 만든다.

  }
}
