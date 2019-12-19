package com.eomcs.basic.ex04.assignment;
import java.util.Scanner;
import java.lang.Math;

public class SimpleCalculator {
  public static void main(String[] args) {
    Scanner scanner = new Scanner (System.in);
    System.out.println("과제1 : 계산기 애플리케이션을 작성하라");
    System.out.println("- 실행");
    System.out.print("값1? ");
    double value1 = scanner.nextDouble();
    System.out.print("값2? ");
    double value2 = scanner.nextDouble();
    scanner.nextLine(); // nextInt와 nextLine 사이에 빈칸제거
    System.out.print("연산자(+,-,*,/)? ");
    String calculator = scanner.nextLine();
    scanner.close();
    double result = 0;
    switch (calculator) {
      case "+": result = value1 + value2; break;
      case "-": result = value1 - value2; break;
      case "*": result = value1 * value2; break;
      case "/": result = value1 / value2; break;
      default : System.out.println("계산할수 없는 연산자입니다."); return;
    } 
    System.out.printf("=> %s %s %s = %s", value1, calculator, value2, result);
  }
}
//과제 1 : 계산기 애플리케이션을 작성하라.
//- 실행
//값1? 10
//값2? 20
//연산자(+,-,*,/)? +
//=> 10 + 20 = 30 