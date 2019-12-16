package com.bitcamp.myproject;
import java.util.Scanner;


public class App2 {
  static Board[] board = new Board[1000];
  static int count = 0;
  public static void main(String[] args) {

    input();
    output();

  }

  static void input() { 
    java.io.InputStream inputStream = System.in;
    Scanner scanner = new Scanner(inputStream);

    for(int i=0 ; ; i++ ) {
      count++;

      Board b = new Board();
      Board b = new Board();
      board[i] = b;
      System.out.print("번호? ");
      b.no = scanner.nextInt();
      scanner.nextLine(); // nextInt와 nextLine 사이의 공란 제거(커서)
      System.out.print("이름? ");
      b.name = scanner.nextLine();
      System.out.print("이메일? ");
      b.email = scanner.nextLine();
      System.out.print("암호? ");
      b.password = scanner.nextLine();
      System.out.print("사진? ");
      b.photo = scanner.nextLine();
      System.out.print("전화? ");
      b.tel = scanner.nextLine();
      System.out.println();
      System.out.print("계속 입력하시겠습니까?(Y/N) ");
      String repeat = scanner.nextLine();
      if(repeat.equals("Y")||repeat.equals("y")) {
        continue;
      } else if(repeat.equals("N")||repeat.equals("n"))
        scanner.close();
      break;
    }
  }

  static void output() {
    for(int i=0 ; i<count ; i++) {
      Board b = board[i];
      System.out.printf("%d, %s , %s       , %s      , %tF\n",
          b.no, b.name, b.email, b.tel, b.startDate);
    }
  }
}
/*
번호? 1
이름? 홍길동
이메일? hong@test.com
암호? 1111
사진? hong.png
전화? 1111-2222

계속 입력하시겠습니까?(Y/n) y

번호? 2
이름? 임꺽정
이메일? lim@test.com
암호? 1111
사진? lim.png
전화? 1111-2223

계속 입력하시겠습니까?(Y/n) y

번호? 3
이름? 전봉준
이메일? jeon@test.com
암호? 1111
사진? jeon.png
전화? 1111-2224

계속 입력하시겠습니까?(Y/n) n

1, 홍길동 , hong@test.com       , 1111-2222      , 2019-01-01
2, 임꺽정 , lim@test.com        , 1111-2223      , 2019-01-01
3, 전봉준 , jeon@test.com       , 1111-2224      , 2019-01-01
 */