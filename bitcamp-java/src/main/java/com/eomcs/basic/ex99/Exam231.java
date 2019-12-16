package com.eomcs.basic.ex99;
import java.util.Scanner;

public class Exam231 {
  public static void main(String[] args) {
    //java.io.InputStream keyboard = System.in;
    //java.util.Scanner scanner = new java.util.Scanner(keyboard); //스캐너는 샤워기헤드다
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    // nextInt() 다음에 nextLine()을 입력할때 공백이 입력되는 문제 해결법.
    System.out.print("입력>");
    int i1 = scanner.nextInt();
    int i2 = scanner.nextInt();
    // next();
    // 공백을 만날때까지 읽는다.
    // nextLine()은 앞에 whitespace를 제거안하니까 문제가 된다.
    // scanner.next();
    // 즉 커서의 위치가 생각한거랑 달라서.
    String s = scanner.next();
    String add = scanner.nextLine();
    scanner.close();
    System.out.println("------------------------------------------------");
    System.out.println(i1);
    System.out.println(i2);
    System.out.println(s);
    System.out.println(add);
    // nextInt()의 문제점? 알아야할점
    // white space (space, tab, new line기호)를 만나면 그 앞까지 받은 값을 int로 만든다.

  }
}
