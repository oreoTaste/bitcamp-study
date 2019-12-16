// 과제 1 : 계산기 애플리케이션을 작성하라.
// - 실행
// 값1? 10
// 값2? 20
// 연산자(+,-,*,/)? +
// => 10 + 20 = 30 
//
package com.eomcs.basic.ex04.assignment;

public class Test01 {
  public static void main(String[] args) {
    java.io.InputStream inputStream = System.in;
    java.util.Scanner scanner = new java.util.Scanner(inputStream);
    int result=0;
    System.out.print("값1: ");
    int value1 = scanner.nextInt();
    System.out.print("값2: ");
    int value2 = scanner.nextInt();
    System.out.print("연산자(+,-,*,/)? ");
    scanner.nextLine();
    String calculator = scanner.nextLine();
    scanner.close();
    
    switch (calculator){
      case "+":  result = value1 + value2; break;
      case "-":  result = value1 - value2; break;
      case "*":  result = value1 * value2; break;
      case "/":  result = value1 / value2; break;
      default : result = 0;
    }
    System.out.println();
    System.out.printf("-> %d %s %d = %d ", value1 , calculator, value2, result);

  }
}
