package com.eomcs.oop.ex01.assignment;

import java.util.Scanner;
import com.eomcs.oop.ex01.Score;

public class Test001 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int SIZE = 100;
    Score[] sc = new Score[SIZE];
    
    for(int i = 0 ; i < 3 ; i++) {
      Score input = new Score();
    System.out.print("입력: ");
      input.name=scanner.next();
      input.kor = scanner.nextInt();
      input.eng = scanner.nextInt();
      input.math = scanner.nextInt();
      input.sum = input.kor + input.eng + input.math;
      input.aver = (float) (input.sum / 3);
      sc[i] = input;
    }
    scanner.close();
    
    System.out.println("-------------------------");
    
    for(int i = 0 ; i < 3 ; i++) {
      Score output = new Score();
      output = sc[i];
      System.out.printf("%s %3d %3d %3d %3d %.1f\n",
          output.name, output.kor, output.eng, output.math, output.sum, output.aver);
    }
  }
}
/*
> java -classpath bin step03.assignment.Test01
입력: 홍길동 100 100 100
입력: 임꺽정 90 90 90
입력: 유관순 80 80 80
--------------------------
홍길동 100 100 100 300 100.0
임꺽정  90  90  90 270  90.0
유관순  80  80  80 240  80.0
>
 */