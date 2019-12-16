package com.bitcamp.myproject;
import java.sql.Date;

public class App {
  public static void main(String[] args) {
    java.io.InputStream inputStream = System.in;
    java.util.Scanner scanner = new java.util.Scanner(inputStream); 
    System.out.print("번호 ");
    int no = scanner.nextInt();
    scanner.nextLine();//nextInt와 nextLine사이 공란 제거(커서옮기기)
    System.out.print("수업명? ");
    String title = scanner.nextLine();
    System.out.print("수업내용? ");
    String contents = scanner.nextLine();
    System.out.print("시작일? ");
    Date startDate = Date.valueOf(scanner.nextLine());
    System.out.print("종료일? ");
    Date endDate = Date.valueOf(scanner.nextLine());
    System.out.print("총수업시간? ");
    int totalHour = scanner.nextInt();
    System.out.print("일수업시간? ");
    int dailyHour = scanner.nextInt();
    scanner.close();
    System.out.println();
    
    System.out.printf("번호: %d\n", no);
    System.out.printf("수업명: %s\n", title);
    System.out.printf("수업내용: %s\n", contents);
    System.out.printf("기간: %tF ~ %tF\n", startDate, endDate);
    System.out.printf("총수업시간: %d시간\n", totalHour);
    System.out.printf("일수업시간: %d시간\n", dailyHour);
  }
}

/*
번호? 1
수업명? 자바 프로젝트 실습
수업내용? 자바 프로젝트를 통한 자바 언어 활용법 익히기
시작일? 2019-01-02
종료일? 2019-05-28
총수업시간? 1000
일수업시간? 8

번호: 1
수업명: 자바 프로젝트 실습
수업내용: 자바 프로젝트를 통한 자바 언어 활용법 익히기
기간: 2019-01-02 ~ 2019-05-28
총수업시간: 1000 시간
일수업시간: 8 시간
 */