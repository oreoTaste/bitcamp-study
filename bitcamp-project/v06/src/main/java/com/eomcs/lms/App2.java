package com.eomcs.lms;

import java.util.Calendar;
import java.sql.Date;
import java.util.Scanner;

public class App2 {
  static int count = 0;
  static Board[] boards = new Board[1000];
  public static void main(String[] args) {


    input();
    output();
  }

  static void input() {
    java.io.InputStream inputStream = System.in;
    java.util.Scanner scanner = new java.util.Scanner(inputStream);
    
    final int SIZE = 100;
    int[] no = new int[SIZE];
    String[] name = new String[SIZE];
    String[] email = new String[SIZE];
    String[] password = new String[SIZE];
    String[] photo = new String[SIZE];
    String[] tel = new String[SIZE];
    Date[] registeredDate = new Date[SIZE];
    int count = 0;
    
    for(count = 0 ; count<SIZE ; count++) {
      System.out.print("번호?");
      no[count] = scanner.nextInt();
      scanner.nextLine(); // 빈칸제거
      System.out.print("이름? ");
      name[count] = scanner.nextLine();
      System.out.print("이메일? ");
      email[count] = scanner.nextLine();
      System.out.print("비밀번호? ");
      password[count] = scanner.nextLine();
      System.out.print("사진? ");
      photo[count] = scanner.nextLine();
      System.out.print("전화? ");
      tel[count] = scanner.nextLine();
      registeredDate[count] = new Date(System.currentTimeMillis());
      System.out.println();
      System.out.print("계속 입력하시겠습니까?(y/n)");
      String repeat = scanner.nextLine();
      if(repeat.equals("Y") || repeat.equals("y")) continue;
      else break;
    } scanner.close();
    
    System.out.println();
    
    for (int i=0 ; i<=count ; i++) {
      System.out.printf("%d,  %s,  %s,  %s,  %s\n",
          no[i], name[i], email[i], tel[i], registeredDate[i]);
    }
    
    /*
    for(int i=0 ; ;i++) {
      count++;
      Board b = new Board();
      System.out.print("번호? ");
      b.no = scanner.nextInt();
      scanner.nextLine();
      System.out.print("이름? ");
      b.name = scanner.nextLine();
      System.out.print("이메일? ");
      b.email = scanner.nextLine();
      System.out.print("비밀번호? ");
      b.password = scanner.nextLine();
      System.out.print("사진? ");
      b.photo = scanner.nextLine();
      System.out.print("전화? ");
      b.tel = scanner.nextLine();
      System.out.println();
      b.startDate = new Date(System.currentTimeMillis());
      
      System.out.print("계속 입력하시겠습니까?(y/n) ");
      String repeat = scanner.nextLine();
      boards[i] = b;
      if(repeat.equals("Y")||repeat.equals("y")) {
        continue;
      } else scanner.close(); break;
    }
    */
  }
  

  static void output() {
    for(int i=0 ; i<count ; i++) {
      System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tH:%5$tM:%5$tS\n",
          boards[i].no, boards[i].name,  boards[i].email,  boards[i].tel , boards[i].startDate );
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