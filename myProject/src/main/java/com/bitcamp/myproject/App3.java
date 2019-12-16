package com.bitcamp.myproject;
import java.util.Scanner;
import java.sql.Date;
import java.util.Calendar;

public class App3 {
  public static void main(String[] args) { 
    java.io.InputStream inputStream = System.in;
    Scanner scanner = new Scanner(inputStream);
    
    System.out.print("번호? ");
    int no = scanner.nextInt();
    scanner.nextLine(); // nextInt와 nextLine사이의 공란제거
    System.out.print("내용? ");
    String contents = scanner.nextLine();
    scanner.close();
    System.out.println();
    
    System.out.printf("번호: %d\n", no);
    System.out.printf("내용: %s\n", contents);
    Date registeredDate = new Date(System.currentTimeMillis());
    System.out.printf("작성일: %1$tY-%1$tm-%1$td "
        + "%1$tH:%1$tM:%1$tS\n", registeredDate);
    int count = 0;
    System.out.printf("조회수: %d", ++count);
    
  }
}
/*
번호? 1
내용? 게시글입니다.

계속 입력하시겠습니까?(Y/n) y

번호? 2
내용? 두 번째 게시글입니다.

계속 입력하시겠습니까?(Y/n) y

번호? 3
내용? 두 번째 게시글입니다.

계속 입력하시겠습니까?(Y/n) n

1, 게시글입니다.              , 2019-01-01, 0
2, 두 번째 게시글입니다.        , 2019-01-01, 0
3, 세 번째 게시글입니다.        , 2019-01-01, 0
 */